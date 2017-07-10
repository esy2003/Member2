package com.hanbit.member.controller;

import java.util.List;

import javax.swing.JOptionPane;

import com.hanbit.member.constants.Butt;
import com.hanbit.member.domain.MemberBean;
import com.hanbit.member.service.MemberService;
import com.hanbit.member.serviceImpl.MemberServiceImpl;

public class MemberController {
	public static void main(String[] args) {
		
		MemberService service = new MemberServiceImpl();
		MemberBean member = null;
		Butt[] buttons = {Butt.EXIT, Butt.ADD, Butt.COUNT, Butt.FIND_NAME, Butt.FIND_ID, Butt.LIST, Butt.UPDATE, Butt.DEL};
		
		
		do {
			flag:
			switch ((Butt) JOptionPane.showInputDialog(
					null, //frame
					"MEMBER ADMIN", // frame title
					"SELECT MENU", //order
					JOptionPane.QUESTION_MESSAGE, //type
					null, // icon
					buttons, // Array of choices
					buttons[1] //default값
					)) {
			case EXIT: return;
			case ADD:
			String[] arr = JOptionPane.showInputDialog("name/id/pw/ssn").split("/");
				member = new MemberBean();
				member.setName(arr[0]);
				member.setUserId(arr[1]);
				member.setUserPw(arr[2]);
				member.setSSN(arr[3]);
				service.addMember(member);
				JOptionPane.showMessageDialog(null, "회원가입성공");
				break flag;
			case COUNT:
				JOptionPane.showMessageDialog(null, service.countMembers() + " 명");	
				break flag;
			case LIST:
	            JOptionPane.showMessageDialog(null, "리스트 : "+ service.getMembers());
	            break flag;
			case FIND_ID:
				//service.findById(JOptionPane.showInputDialog("조회할 아이디 입력"));
				
				// JOptionPane.showMessageDialog 안에는 toString() 이 생략이 가능하다(자주 쓰다보니 자동추가됨)
				JOptionPane.showMessageDialog(null, service.findById(JOptionPane.showInputDialog("조회할 아이디 입력")));
				break flag;
			case FIND_NAME:
				List<MemberBean> membersName = service.findByName(JOptionPane.showInputDialog("찾고자 하는 아이디의 이름을 입력해주세요"));
				String result = "";
				if (membersName.size() ==0) {
					result = "조회 가능한 이름이 없습니다";
				}
				else {
					for (int i=0;i<membersName.size();i++){
						result += membersName.get(i) + "\n";
					}
				}
				JOptionPane.showMessageDialog(null, "리스트 : " + result);
				break flag;
			case UPDATE:
				member = new MemberBean();
				JOptionPane.showInputDialog("아이디");
				member.setUserPw(JOptionPane.showInputDialog("변경할 비밀번호"));
				member.setName(JOptionPane.showInputDialog("변경할 이름"));
				member.setSSN(JOptionPane.showInputDialog("변경할 주민번호"));
				service.updatePass(member);
				JOptionPane.showMessageDialog(null, "수정완료");
				
				/*	MemberBean memberList = new MemberBean();
				memberList.setUserId(JOptionPane.showInputDialog("아이디/비번"));
				service.updatePass(memberList);
				JOptionPane.showMessageDialog(null, "수정완료");
*/				break flag;
			case DEL:
				String deleteId = JOptionPane.showInputDialog("삭제할 아이디");
				service.delete(deleteId);
				JOptionPane.showMessageDialog(null, "삭제완료");
				break flag;
			}
		}while(true);
	}
}
