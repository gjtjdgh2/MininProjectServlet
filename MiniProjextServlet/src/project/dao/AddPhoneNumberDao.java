package project.dao;

import java.util.*;



public interface AddPhoneNumberDao {

	public List<AddPhoneNumberVo> getList();
	public List<AddPhoneNumberVo> search(String keyword);
	public AddPhoneNumberVo get(Long id);
	public int insert(AddPhoneNumberVo vo);
	public int delete(Long id);
}
