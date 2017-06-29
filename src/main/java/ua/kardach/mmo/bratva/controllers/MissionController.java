package ua.kardach.mmo.bratva.controllers;

import java.awt.TrayIcon.MessageType;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.kardach.mmo.bratva.model.Mission;
import ua.kardach.mmo.bratva.model.Personage;
import ua.kardach.mmo.bratva.model.Podgon;
import ua.kardach.mmo.bratva.model.Quest;
import ua.kardach.mmo.bratva.model.Role;
import ua.kardach.mmo.bratva.model.User;
import ua.kardach.mmo.bratva.model.service.BoostService;
import ua.kardach.mmo.bratva.model.service.MissionService;
import ua.kardach.mmo.bratva.model.service.PodgonService;
import ua.kardach.mmo.bratva.model.service.QuestService;
import ua.kardach.mmo.bratva.util.Message;
import ua.kardach.mmo.bratva.util.Util;

@Controller
public class MissionController {
	@Autowired
	private MissionService missionService;
	@Autowired
	private QuestService questService;
	@Autowired
	private BoostService boostService;
	@Autowired
	private PodgonService podgonService;
	
	String[] businessMenuArray = { "mission", "business"};
	List<String> businessMenuList = Arrays.asList(businessMenuArray);
	
	@RequestMapping("mission")
	public String showMissionPage(HttpSession session, Model model){
		Mission mission = getSessionMission(session);
		return "redirect:/mission/" + mission.getId();
	}
	
	@RequestMapping("mission/{missionId}")
	public String showMissionPage(@PathVariable long missionId, HttpSession session, Model model){
		Mission mission = getSessionMission(session);
		if(mission.getId() != missionId){
			mission = addSessionMission(session, missionId);
		}		
		
		initCommmonModel(model);
		return "mission";
	}
	
	@RequestMapping("mission/{missionId}/quest/{questId}")
	public String qoQuest(@PathVariable long questId, HttpSession session, Model model){
		Message message = (Message) session.getAttribute("message");
		User user = (User) session.getAttribute("hero");
		Personage pers = user.getPersonage();
		Quest quest = questService.getQuest(user.getId(), questId);
		long missionId = quest.getMissionId();
		Mission mission = getSessionMission(session);		
		if(mission.getId() != missionId){
			mission = addSessionMission(session, missionId);
		}
		
		
		int procMoney = pers.getBoostMoney() + pers.getRoleProcent(Role.Param.MONEY_MISSION);
		boostService.removeBoosts(pers, false);
		int minusEnergy = quest.getEnergy();
		int rand = pers.getRoleProcent(Role.Param.ENERGY_MISSION);
		if(rand >= Util.rand(1, 100)){
			minusEnergy = 0;
		}
		int bonusAuthority = quest.getAuthority();
		int procAuthority = pers.getBoostAuthority();
		if(procAuthority != 0){
			bonusAuthority += bonusAuthority * procAuthority / 100;
		}
		int bonusMoney = quest.getMoney();
		if(procMoney != 0 && bonusMoney > 0){
			bonusMoney += bonusMoney * procMoney / 100;
		}
		int heroEnergy = pers.getEnergy();
		if(heroEnergy < minusEnergy){
			Podgon energyPodgon = podgonService.getFirstEnergyPodgon(pers.getUserPodgons());
			if(energyPodgon != null){
				//message.init(Message.Type.TREAT_ENERGY_BY_PODGON, true);
				model.addAttribute("treatPodgon", energyPodgon);
			}
			else{
				model.addAttribute("treat", 2);
				//message.init(Message.Type.TREAT_ENERGY_BY_CASH, true);
			}
		}
		else{
			if(bonusMoney < 0 && pers.getMoney() + bonusMoney < 0){
				
			}
		}
		
		initCommmonModel(model);
		return "mission";
	}
	
	private Mission getSessionMission(HttpSession session){
		Mission mission = (Mission)session.getAttribute("mission");
		if(mission == null){
			long missionId = 1;
			mission = addSessionMission(session, missionId);
		}
		return mission;
	}
	
	private Mission addSessionMission(HttpSession session, long missionId){
		User user = (User)session.getAttribute("hero");
		Mission mission = missionService.getMission(user.getId(), missionId);
		session.setAttribute("mission", mission);
		return mission;
	}
	
	private void initCommmonModel(Model model){
		model.addAttribute("title", "mission");
		model.addAttribute("businessMenuList", businessMenuList);
		model.addAttribute("amountMissions", missionService.amountAllMission());
		model.addAttribute("menuBottomBack", false);
		model.addAttribute("menuBottomMenu", true);
		model.addAttribute("menuBottomHelp", true);
	}
}
