package com.worldpara.service;

import com.worldpara.domain.*;
import com.worldpara.mapper.inf.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by chin39 on 2017/6/27.
 */

@Service
public class UserService {

	private final Logger log = Logger.getLogger(BookService.class);

	private UserMapper userMapper;
	private StaffMapper staffMapper;
	private ReaderMapper readerMapper;
	private RoleUserRecordMapper roleUserRecordMapper;
	private RoleRecordMapper roleRecordMapper;

	@Autowired
	public UserService(UserMapper userMapper, StaffMapper staffMapper, ReaderMapper readerMapper,
	                   RoleUserRecordMapper roleUserRecordMapper, RoleRecordMapper roleRecordMapper) {
		this.userMapper = userMapper;
		this.staffMapper = staffMapper;
		this.readerMapper = readerMapper;
		this.roleUserRecordMapper = roleUserRecordMapper;
		this.roleRecordMapper = roleRecordMapper;
	}

	public User userLogin(String username, String password) {
		//检查是否符合登录信息，返回用户类
		List<User> userList = userMapper.selectByUsername(username);
		if (userList.isEmpty()) {
			return null;
		}
		if (userList.get(0).getPassword().equals(password)) {
			log.info("登录成功");
			userList.get(0).setLastLoginTime(new Date());
			userMapper.updateByPrimaryKeySelective(userList.get(0));
			return userList.get(0);
		} else {
			return null;
		}
	}

	public int userAdd(User user) throws RuntimeException {
		//添加用户 返回情况代码 默认注册的是读者用户
		List<User> userList = userMapper.searchByUsername(user.getUsername());
		if (userList.isEmpty()) {
			Reader reader = new Reader();
			userMapper.insertSelective(user);
			//将 reader id 与 user id 设为相同
			List<User> userList1 = userMapper.searchByUsername(user.getUsername());
			reader.setReaderId(userList1.get(0).getUserId());
			if (readerMapper.selectByPrimaryKey(userList1.get(0).getUserId()) != null) {
				return readerMapper.insertSelective(reader);
			}
			return 0;
		} else {
			throw new RuntimeException("用户已存在");
		}
	}

	public List<User> getUserByUsername(String username) {
		return userMapper.selectByUsername(username);
	}

	public int userMod(User user) {
		// 输入用户类，修改用户信息
		return userMapper.updateByPrimaryKeySelective(user);
	}

	public int userDisable(User user) {
		//禁用用户
		user.setStatus("0");
		return userMod(user);
	}

	public int readerToStaff(User user) {
		//将reader用户转换为 staff 用户
		readerMapper.deleteByPrimaryKey(user.getUserId());
		Staff staff = new Staff();
		staff.setStaffId(user.getUserId());
		return staffMapper.insertSelective(staff);
	}

	public Reader findReaderByUserID(int userID){
		return readerMapper.selectByPrimaryKey(userID);
	}

	public int roleUserRelation(User user, RoleRecord role) {
		// 将用户与特定角色关联
		RoleUserRecord roleUserRecord = new RoleUserRecord();
		roleUserRecord.setRoleId(role.getRoleId());
		roleUserRecord.setUserId(user.getUserId());
		return roleUserRecordMapper.insertSelective(roleUserRecord);
	}

	public int setroleUserRelationByID(int userID, int roleID) {
		// 将用户与特定角色关联
		RoleUserRecord roleUserRecord = new RoleUserRecord();
		roleUserRecord.setRoleId(roleID);
		roleUserRecord.setUserId(userID);
		return roleUserRecordMapper.insertSelective(roleUserRecord);
	}


	public void updateUserRoleRecords(int userID, List<String> newRoleList) {

		User user = getUserByID(userID);

		List<RoleUserRecord> roleUserRecordList = roleUserRecordMapper.selectByUserID(userID);
		for (RoleUserRecord roleUserRecord : roleUserRecordList) {
			if (!newRoleList.contains(roleRecordMapper.selectByPrimaryKey(roleUserRecord.getRoleId()).getRoleType())) {
				roleUserRecordMapper.deleteByPrimaryKey(roleUserRecord.getRelationId());
			}
		}

		List<String> oldRoleList = getRoleStringByUser(user);

		List<String> updateRoles = newRoleList;
		updateRoles.removeAll(oldRoleList);

		for (String role : updateRoles) {
			List<RoleRecord> roleRecordList = getRoleByType(role);
			if (roleRecordList.isEmpty()) {
				throw new RuntimeException("找不到角色");
			}
			setroleUserRelationByID(userID, roleRecordList.get(0).getRoleId());
		}
	}

	public List<RoleRecord> getRoleByType(String type) {
		return roleRecordMapper.selectByRoleType(type);
	}

	public List<RoleRecord> getRoleByUser(User user) {
		log.info("返回该用户所有角色");
		List<RoleUserRecord> roleUserRecords = roleUserRecordMapper.selectByUserID(user.getUserId());
		return roleUserRecords.stream().map(roleUserRecord -> roleRecordMapper.selectByPrimaryKey(roleUserRecord.getRoleId())).collect(Collectors.toList());
	}

	public List<String> getRoleStringByUser(User user) {
		List<RoleRecord> roleRecords = getRoleByUser(user);
		return roleRecords.stream().map(RoleRecord::getRoleType).collect(Collectors.toList());
	}

	public List<String> getAllRoleStrings() {
		return IntStream.range(1, 5).mapToObj(i -> roleRecordMapper.selectByPrimaryKey(i).getRoleType()).collect(Collectors.toList());
	}

	public List<UserRoles> createUserRoleLists(List<User> userList) {
		List<UserRoles> userRolesList = new ArrayList<>();
		for (User user : userList) {
			UserRoles userRoles = new UserRoles();
			List<String> roleRecords = getRoleStringByUser(user);
			userRoles.setUser(user);
			userRoles.setRoleLists(roleRecords);
			userRolesList.add(userRoles);
		}

		return userRolesList;
	}

	public User getUserByID(int userID) {
		return userMapper.selectByPrimaryKey(userID);
	}
}
