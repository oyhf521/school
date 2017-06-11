package io.school.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.school.dao.PersonDao;
import io.school.entity.PersonEntity;
import io.school.service.PersonService;

@Service("personService")
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonDao personDao;

	@Override
	public PersonEntity queryObject(Integer id) {
		return personDao.queryObject(id);
	}

	@Override
	public List<PersonEntity> queryList(Map<String, Object> map) {
		return personDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return personDao.queryTotal(map);
	}

	@Override
	public void save(PersonEntity person) {
		personDao.save(person);
	}

	@Override
	public void update(PersonEntity person) {
		personDao.update(person);
	}

	@Override
	public void delete(Integer id) {
		personDao.delete(id);
	}

	@Override
	public void deleteBatch(Integer[] ids) {
		personDao.deleteBatch(ids);
	}

	@Override
	public void setStatus(Integer[] ids, String status) {
		if (null != ids&&StringUtils.isNotEmpty(status)) {
			for (Integer id : ids) {
				PersonEntity person = queryObject(id);
				person.setStatus(status);
				personDao.update(person);
			}
		}
	}

}
