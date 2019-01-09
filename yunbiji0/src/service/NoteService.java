package service;

import util.NoteResult;
import vo.SearchNoteParams;

public interface NoteService {
	public NoteResult searchNotes(SearchNoteParams params);
	public NoteResult moveNote(String noteId,String bookId);
	public NoteResult loadShare(String shareId);
	public NoteResult searchShare(String keyword);
	public NoteResult shareNote(String noteId);
	public NoteResult updateNote(
		String noteTitle,String noteBody,String noteId);
	public NoteResult addNote(
		String bookId,String userId,String noteTitle);
	public NoteResult loadNoteDetail(String id);
	public NoteResult loadBookNotes(String bookId);
}
