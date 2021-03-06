package com.cwkj.ysms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cwkj.ysms.dao.CoachAttDao;
import com.cwkj.ysms.dao.CoachDao;
import com.cwkj.ysms.dao.JobsDao;
import com.cwkj.ysms.dao.SchoolDao;
import com.cwkj.ysms.dao.TeammemberDao;
import com.cwkj.ysms.model.YsmsCoach;
import com.cwkj.ysms.model.YsmsCoachAtt;
import com.cwkj.ysms.model.YsmsJobs;
import com.cwkj.ysms.model.YsmsSchool;
import com.cwkj.ysms.model.YsmsTeammember;
import com.cwkj.ysms.model.view.CoachManagerListView;
import com.cwkj.ysms.service.CoachManagementService;
import com.cwkj.ysms.util.Page;

@Service
public class CoachManagementServiceImpl implements CoachManagementService {

	@Resource
	private CoachDao coachDao;

	@Resource
	private SchoolDao schoolDao;

	@Resource
	private CoachAttDao coachAttDao;
	
	@Resource
	private JobsDao jobsDao;

	public CoachAttDao getCoachAttDao() {
		return coachAttDao;
	}

	public void setCoachAttDao(CoachAttDao coachAttDao) {
		this.coachAttDao = coachAttDao;
	}

	public JobsDao getJobsDao() {
		return jobsDao;
	}

	public void setJobsDao(JobsDao jobsDao) {
		this.jobsDao = jobsDao;
	}

	public CoachAttDao getCoachPhotoDao() {
		return coachAttDao;
	}

	public void setCoachPhotoDao(CoachAttDao coachPhotoDao) {
		this.coachAttDao = coachPhotoDao;
	}

	public CoachDao getCoachDao() {
		return coachDao;
	}

	public void setCoachDao(CoachDao coachDao) {
		this.coachDao = coachDao;
	}

	public SchoolDao getSchoolDao() {
		return schoolDao;
	}

	public void setSchoolDao(SchoolDao schoolDao) {
		this.schoolDao = schoolDao;
	}

	@Resource
	private TeammemberDao teammemberDao;

	public TeammemberDao getTeammemberDao() {
		return teammemberDao;
	}

	public void setTeammemberDao(TeammemberDao teammemberDao) {
		this.teammemberDao = teammemberDao;
	}

	@Override
	public boolean addCoach(String identifiedId, Integer schoolId,
			String coachContact, String identifiedName,
			Integer identifiedGender, Date identifiedBirthday,
			String identifiedAddress, String identifiedNationality,
			String photoBase64, String landPhone, int jobIndex, int coachLevel, String coachattachment,
			boolean schoolcoachFlag, String schoolattachment) {
		try {
			YsmsCoach ysmsCoach = new YsmsCoach();
			YsmsSchool ysmsSchool = schoolDao.findById(schoolId);
			ysmsCoach.setDeleteflag(0);
			ysmsCoach.setYsmsSchool(ysmsSchool);
			ysmsCoach.setCoachMobile(coachContact);
			ysmsCoach.setCoachPhone(landPhone);
			ysmsCoach.setIdentifiedId(identifiedId);
			ysmsCoach.setIdentifiedName(identifiedName);
			ysmsCoach.setIdentifiedGender(identifiedGender);
			ysmsCoach.setIdentifiedBirthday(identifiedBirthday);
			ysmsCoach.setIdentifiedAddress(identifiedAddress);
			ysmsCoach.setIdentifiedNationality(identifiedNationality);
			ysmsCoach.setCoachLevel(coachLevel);
			ysmsCoach.setSchoolcoachFlag(schoolcoachFlag);
			YsmsJobs ysmsJobs = jobsDao.findById(jobIndex);
			ysmsCoach.setYsmsJobs(ysmsJobs);
			coachDao.save(ysmsCoach);

			System.out.println("school:" + schoolattachment);
			System.out.println("coach:" + coachattachment);
			YsmsCoachAtt ysmsCoachAtt = new YsmsCoachAtt();
			ysmsCoachAtt.setAttName(photoBase64);
			ysmsCoachAtt.setAttType(0);
			ysmsCoachAtt.setYsmsCoach(ysmsCoach);
			coachAttDao.save(ysmsCoachAtt);
			if (schoolcoachFlag) {
				YsmsCoachAtt ysmsCoachAtt1 = new YsmsCoachAtt();
				ysmsCoachAtt1.setAttName(schoolattachment);
				ysmsCoachAtt1.setAttType(1);
				ysmsCoachAtt1.setYsmsCoach(ysmsCoach);
				coachAttDao.save(ysmsCoachAtt1);
			}

			if (coachLevel > 0 && coachLevel < 5) {
				YsmsCoachAtt ysmsCoachAtt2 = new YsmsCoachAtt();
				ysmsCoachAtt2.setAttName(coachattachment);
				ysmsCoachAtt2.setAttType(2);
				ysmsCoachAtt2.setYsmsCoach(ysmsCoach);
				coachAttDao.save(ysmsCoachAtt2);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCoach(Integer coachId, String identifiedId,
			Integer schoolId, String coachContact, String identifiedName,
			Integer identifiedGender, Date identifiedBirthday,
			String identifiedAddress, String identifiedNationality,
			int coachLevel, boolean schoolcoachFlag) {
		try {
			YsmsCoach ysmsCoach = coachDao.findById(coachId);
			YsmsSchool ysmsSchool = schoolDao.findById(schoolId);
			ysmsCoach.setYsmsSchool(ysmsSchool);
			ysmsCoach.setCoachMobile(coachContact);
			ysmsCoach.setIdentifiedId(identifiedId);
			ysmsCoach.setIdentifiedName(identifiedName);
			ysmsCoach.setIdentifiedGender(identifiedGender);
			ysmsCoach.setIdentifiedBirthday(identifiedBirthday);
			ysmsCoach.setIdentifiedAddress(identifiedAddress);
			ysmsCoach.setIdentifiedNationality(identifiedNationality);
			ysmsCoach.setCoachLevel(2);
			ysmsCoach.setSchoolcoachFlag(false);
			coachDao.updata(ysmsCoach);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteCoach(Integer coachId) {
		try {
			YsmsCoach ysmsCoach = coachDao.findById(coachId);
			List<YsmsTeammember> list = teammemberDao.findByCoachId(coachId);
			// 删除所有球队关系
			for (int i = 0; i < list.size(); i++) {
				YsmsTeammember ysmsTeammember = list.get(i);
				teammemberDao.delete(ysmsTeammember);
			}
			ysmsCoach.setDeleteflag(1);
			coachDao.updata(ysmsCoach);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public YsmsCoach getCoachByID(Integer coachId) {
		YsmsCoach ysmsCoach = coachDao.findById(coachId);
		return ysmsCoach;
	}

	@Override
	public List<YsmsCoach> getCoaches(String identifiedId, Integer schoolId,
			String coachContact, String identifiedName, Integer identifiedGender) {
		List<YsmsCoach> coachList = coachDao.findByFuzzyQuery(identifiedId,
				schoolId, coachContact, identifiedName, identifiedGender);
		return coachList;
	}

	@Override
	public List<YsmsCoach> getCoachesByPage(String identifiedId,
			Integer schoolId, String coachContact, String identifiedName,
			Integer identifiedGender, Page page) {
		List<YsmsCoach> coachList = coachDao.findByFuzzyQueryAndPage(
				identifiedId, schoolId, coachContact, identifiedName,
				identifiedGender, page);
		return coachList;
	}

	@Override
	public List<YsmsCoach> getCoachesByPageAndOrder(String identifiedId,
			Integer schoolId, String coachContact, String identifiedName,
			Integer identifiedGender, Page page, String order) {
		return coachDao.findByFuzzyQueryByPageAndOrder(identifiedId, schoolId,
				coachContact, identifiedName, identifiedGender, page, order);
	}

	@Override
	public int getCoachesByPageAndOrderCount(String identifiedId,
			Integer schoolId, String coachContact, String identifiedName,
			Integer identifiedGender) {
		return coachDao.findByFuzzyQueryAndPageCount(identifiedId, schoolId,
				coachContact, identifiedName, identifiedGender);
	}

	@Override
	public List<CoachManagerListView> getCoachesManagerListViewByPageAndOrder(
			String identifiedId, Integer schoolId, String coachContact,
			String identifiedName, Integer identifiedGender, Page page,
			String orderString) {
		List<CoachManagerListView> coachManagerListViews = new ArrayList<CoachManagerListView>();
		List<YsmsCoach> ysmsCoachs = getCoachesByPageAndOrder(identifiedId,
				schoolId, coachContact, identifiedName, identifiedGender, page,
				orderString);
		for (YsmsCoach ysmsCoach : ysmsCoachs) {
			Set<YsmsCoachAtt> coachAtts = ysmsCoach.getYsmsCoachAtts();
			YsmsCoachAtt ysmsCoachID = null, ysmsCoachSchool = null, ysmsCoachLeve = null;
			for (YsmsCoachAtt ysmsCoachAtt : coachAtts) {
				switch (ysmsCoachAtt.getAttType()) {
				case 0:
					ysmsCoachID = ysmsCoachAtt;
					break;
				case 1:
					ysmsCoachSchool = ysmsCoachAtt;
					break;
				case 2:
					ysmsCoachLeve = ysmsCoachAtt;
					break;
				}
			}
			CoachManagerListView coachManagerListView = new CoachManagerListView(
					ysmsCoach, ysmsCoachID, ysmsCoachSchool, ysmsCoachLeve);
			coachManagerListViews.add(coachManagerListView);
		}
		return coachManagerListViews;
	}

	@Override
	public CoachManagerListView getCoachListViewByID(int coachId) {
		return coachDao.getCoachListViewByID(coachId);
		// YsmsCoach ysmsCoach=getCoachByID(coachId);
		// Set<YsmsCoachAtt> coachAtts= ysmsCoach.getYsmsCoachAtts();
		// YsmsCoachAtt ysmsCoachID = null, ysmsCoachSchool = null,
		// ysmsCoachLeve = null;
		// for (YsmsCoachAtt ysmsCoachAtt : coachAtts) {
		// switch (ysmsCoachAtt.getAttType()) {
		// case 0:
		// ysmsCoachID=ysmsCoachAtt;
		// break;
		// case 1:
		// ysmsCoachSchool=ysmsCoachAtt;
		// break;
		// case 2:
		// ysmsCoachLeve=ysmsCoachAtt;
		// break;
		// }
		// }
		// return new CoachManagerListView(ysmsCoach, ysmsCoachID,
		// ysmsCoachSchool, ysmsCoachLeve);
	}

	@Override
	public boolean updateCoach(int coachId, Integer schoolId, String coachPhone, String coachLandPhone,
			int jobIndex, int coachLevel, boolean schoolcoachFlag, String image) {
		YsmsCoach coach = coachDao.findById(coachId);
		coach.setSchoolcoachFlag(schoolcoachFlag);
		coach.setCoachLevel(coachLevel);
		coach.setCoachMobile(coachPhone);
		coach.setCoachPhone(coachLandPhone);
		coach.setYsmsJobs(jobsDao.findById(jobIndex));
		coachDao.save(coach);
		YsmsCoachAtt coachAtt = coachAttDao.findByCoachId(coachId);
		if(!"".equals(image)&&image!=null){
			coachAtt.setAttName(image);
			coachAttDao.save(coachAtt);
		}
		return true;
	}
	
	@Override
	public List<YsmsJobs> getAllJobs() {
		// TODO Auto-generated method stub
		return jobsDao.findAllBesidesStudent();
	}

}
