package com.hanbit.member.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.hanbit.member.domain.MemberBean;
import com.hanbit.member.service.MemberService;

public class MemberServiceImpl implements MemberService {
	MemberBean member;
	List<MemberBean> list;
	//생성자 처리가 이 문제의 핵심(아래)
	public MemberServiceImpl() {
		list = new ArrayList<>();
		member = new MemberBean();
	}
	@Override
	public void addMember(MemberBean member) {
		list.add(member);
	}
	@Override
	public List<MemberBean> getMembers() {
		return list;
	}

	@Override
	public int countMembers() {
		return list.size();
	}
	@Override
	public MemberBean findById(String id) {
		member = new MemberBean();
		for (MemberBean m:list) {
			if ((id).equals(m.getUserId())) {
				member = m;
			}
		}
		return member;
	}
	@Override
	public List<MemberBean> findByName(String name) {
		List<MemberBean> temp = new ArrayList<>();
		int count = 0;
		for (MemberBean m:list) {
			if ((name).equals(m.getName())) {
				count++;
			}
		}
		for (MemberBean m:list) {
			if ((name).equals(m.getName())) {
				temp.add(m);
				if (count==temp.size()) {
					break;
				}
			}
		}
		return temp;
	}
	@Override
	public void updatePass(MemberBean bean) {
/*		 member = findById(bean.getUserId());
		 member.setUserPw(bean.getUserPw());*/
		for (MemberBean m:list) {
			if (bean.getUserId().equals(m.getUserId())) {
				if (!bean.getName().equals("")) {
					m.setName(bean.getName());
				}
				if (!bean.getSSN().equals("")) {
					m.setSSN(bean.getSSN());
				}
				if (!bean.getUserPw().equals("")) {
					m.setUserPw(bean.getUserPw());
				}
			}
		}
	}
	@Override
	public void delete(String id) {
		
		for (MemberBean m:list) {
			if (id.equals(m.getUserId())) {
				list.remove(m);
			}
		}
	}
}
