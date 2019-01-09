package service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.cloudnote.dao.UserDao;
import org.tarena.cloudnote.entity.User;
import org.tarena.cloudnote.util.NoteResult;
import org.tarena.cloudnote.util.NoteUtil;

@Service//ɨ��,��Ӧ<context:component-scan/>
@Transactional
public class UserServiceImpl 
implements UserService{
	@Resource
	private UserDao userDao;

	public NoteResult checkLogin(
		String name, String password) {
		NoteResult result = new NoteResult();
		//����û���
		User user = userDao.findByName(name);
		if(user == null){
			result.setStatus(1);
			result.setMsg("�û�������");
			return result;
		}
		//�������
		String md5_password = NoteUtil.md5(password);
		//���û������1234���ļ��ܺ����ݿ����ĶԱ�
		if(!user.getCn_user_password().equals(md5_password)){
			result.setStatus(2);
			result.setMsg("�������");
			return result;
		}
		//�û�����������ȷ
		result.setStatus(0);
		result.setMsg("�û�����������ȷ");
		result.setData(user.getCn_user_id());
		return result;
	}

	public NoteResult regist(
		String name, String password, String nick) {
		NoteResult result = new NoteResult();
		//��Ҫ���ݼ��
		User has_user = userDao.findByName(name);
		if(has_user != null){
			result.setStatus(1);
			result.setMsg("�û����ѱ�ռ��");
			return result;
		}
		//��Ӵ���
		User user = new User();
		user.setCn_user_name(name);//�û���
		user.setCn_user_nick(nick);//�ǳ�
		String md5_password = NoteUtil.md5(password);
		user.setCn_user_password(md5_password);//��������
		user.setCn_user_token(null);//����
		String userId = NoteUtil.createId();
		user.setCn_user_id(userId);//�û�ID
		userDao.save(user);
		//�����߼�����,ģ���쳣
		String s = null;s.length();
		result.setStatus(0);
		result.setMsg("ע��ɹ�");
		return result;
	}

}
