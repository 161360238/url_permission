package cn.wj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.wj.bean.ActiveUser;
import cn.wj.bean.SysPermission;
import cn.wj.bean.SysUser;
import cn.wj.bean.SysUserExample;
import cn.wj.bean.SysUserExample.Criteria;
import cn.wj.exception.CustomException;
import cn.wj.mapper.SysPermissionMapperCustom;
import cn.wj.mapper.SysUserMapper;
import cn.wj.service.SysService;
import cn.wj.util.MD5;

public class SysServiceImpl implements SysService{

	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysPermissionMapperCustom sysPermissionMapperCustom;
	
	@Override
	public ActiveUser authenticat(String userCode, String password) throws Exception {
		//�����û��˺Ų�ѯ���ݿ� �Ƿ��������û�
		SysUser sysUser=this.findSysUserByUserCode(userCode);
		if(sysUser==null){
			throw new CustomException("�û��˻�������");
		}
		//���ݿ�����루md5���ܣ�
		String password_db=sysUser.getPassword();
		String password_input_md5=new MD5().getMD5ofStr(password);
		if(!password_input_md5.equalsIgnoreCase(password_db)){
			throw new CustomException("�û������������");
		}
		String userid=sysUser.getId();
		List<SysPermission> menus=this.findMenuListByUserId(userid);
		List<SysPermission> permissions=this.findPermissionListByUserId(userid);
		//��֤ͨ���������û������Ϣ
				ActiveUser activeUser = new ActiveUser();
				activeUser.setUserid(sysUser.getId());
				activeUser.setUsercode(userCode);
				activeUser.setUsername(sysUser.getUsername());//�û�����
				activeUser.setMenus(menus);
				activeUser.setPermissions(permissions);
		return activeUser;
	}

	public SysUser findSysUserByUserCode(String userCode){
		SysUserExample example=new SysUserExample();
		Criteria criteria=example.createCriteria();
		criteria.andUsercodeEqualTo(userCode);
		List<SysUser> list=sysUserMapper.selectByExample(example);
		if(list.size()==1&&list!=null){
			return list.get(0);
		}
		return null;
	}
	public List<SysPermission> findMenuListByUserId(String userid)
			throws Exception {
		
		return sysPermissionMapperCustom.findMenuListByUserId(userid);
	}

	public List<SysPermission> findPermissionListByUserId(String userid)
			throws Exception {
		
		return sysPermissionMapperCustom.findPermissionListByUserId(userid);
	}
	
}
