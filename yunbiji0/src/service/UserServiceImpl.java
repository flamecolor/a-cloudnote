package service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.cloudnote.dao.UserDao;
import org.tarena.cloudnote.entity.User;
import org.tarena.cloudnote.util.NoteResult;
import org.tarena.cloudnote.util.NoteUtil;

@Service//扫描,对应<context:component-scan/>
@Transactional
public class UserServiceImpl 
implements UserService{
	@Resource
	private UserDao userDao;

	public NoteResult checkLogin(
		String name, String password) {
		NoteResult result = new NoteResult();
		//检查用户名
		User user = userDao.findByName(name);
		if(user == null){
			result.setStatus(1);
			result.setMsg("用户不存在");
			return result;
		}
		//检查密码
		String md5_password = NoteUtil.md5(password);
		//将用户输入的1234明文加密和数据库密文对比
		if(!user.getCn_user_password().equals(md5_password)){
			result.setStatus(2);
			result.setMsg("密码错误");
			return result;
		}
		//用户名和密码正确
		result.setStatus(0);
		result.setMsg("用户名和密码正确");
		result.setData(user.getCn_user_id());
		return result;
	}

	public NoteResult regist(
		String name, String password, String nick) {
		NoteResult result = new NoteResult();
		//必要数据检查
		User has_user = userDao.findByName(name);
		if(has_user != null){
			result.setStatus(1);
			result.setMsg("用户名已被占用");
			return result;
		}
		//添加处理
		User user = new User();
		user.setCn_user_name(name);//用户名
		user.setCn_user_nick(nick);//昵称
		String md5_password = NoteUtil.md5(password);
		user.setCn_user_password(md5_password);//设置密码
		user.setCn_user_token(null);//令牌
		String userId = NoteUtil.createId();
		user.setCn_user_id(userId);//用户ID
		userDao.save(user);
		//其他逻辑处理,模拟异常
		String s = null;s.length();
		result.setStatus(0);
		result.setMsg("注册成功");
		return result;
	}

}
