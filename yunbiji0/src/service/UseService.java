package service;

import util.NoteResult;

public interface UserService {
	public NoteResult checkLogin(
		String name,String password);
	
	public NoteResult regist(
		String name,String password,String nick);
}
