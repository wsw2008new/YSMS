package com.cwkj.ysms.service;

import java.util.Date;
import java.util.List;

import com.cwkj.ysms.model.YsmsAthlete;
import com.cwkj.ysms.model.YsmsAthleteAtt;
import com.cwkj.ysms.model.YsmsDiploma;
import com.cwkj.ysms.model.YsmsJobs;
import com.cwkj.ysms.model.YsmsSchool;
import com.cwkj.ysms.util.Page;

/**
 * 
 * 运动员信息模块接口 提供对运动员信息的新增，修改，删除和查询方法；
 * 
 * @author panhailin henry_pan@126.com
 * @date 2015年3月5日 下午3:33:11
 *
 */
public interface AthleteManagementService {
	
	/**
	 * @param schoolId
	 * @param athleteGuardian1name
	 * @param athleteGuardian2name
	 * @param athleteGuardian1mobi
	 * @param athleteGuardian2mobi
	 * @param athleteGuardian1DiplomaId
	 * @param athleteGuardian2DiplomaId
	 * @param athleteGuardian1JobId
	 * @param athleteGuardian2JobId
	 * @param identifiedId
	 * @param identifiedName
	 * @param identifiedGender
	 * @param identifiedBirthday
	 * @param identifiedAddress
	 * @param identifiedNationality
	 * @param athleteFootsize
	 * @param athleteHeight
	 * @param athleteWeight
	 * @param athletePosition
	 * @param studentId
	 * @param athleteSchoolyear
	 * @param athletePhone
	 * @param photoBase64
	 * @return
	 */
	public boolean addAthlete(int schoolId, String athleteGuardian1name,String athleteGuardian2name,
			String athleteGuardian1mobi, String athleteGuardian2mobi,int athleteGuardian1DiplomaId,
			int athleteGuardian2DiplomaId, int athleteGuardian1JobId, int athleteGuardian2JobId,
			String identifiedId,String identifiedName,int identifiedGender, Date identifiedBirthday,
			String identifiedAddress,String identifiedNationality, int athleteFootsize,
			int athleteHeight,int athleteWeight, int athletePosition, String studentId, 
			String athleteSchoolyear, String athletePhone, String photoBase64, String coachName);
	
	/**
	 * @param athleteId
	 * @param schoolId
	 * @param athleteGuardian1name
	 * @param athleteGuardian2name
	 * @param athleteGuardian1mobi
	 * @param athleteGuardian2mobi
	 * @param athleteGuardian1DiplomaId
	 * @param athleteGuardian2DiplomaId
	 * @param athleteGuardian1JobId
	 * @param athleteGuardian2JobId
	 * @param identifiedId
	 * @param identifiedName
	 * @param identifiedGender
	 * @param identifiedBirthday
	 * @param identifiedAddress
	 * @param identifiedNationality
	 * @param athleteFootsize
	 * @param athleteHeight
	 * @param athleteWeight
	 * @param athletePosition
	 * @param studentId
	 * @param athleteSchoolyear
	 * @param athletePhone
	 * @param photoBase64
	 * @return
	 */
	public boolean updateAthlete(int athleteId, String athleteGuardian1name,String athleteGuardian2name,
			String athleteGuardian1mobi, String athleteGuardian2mobi,int athleteGuardian1DiplomaId,
			int athleteGuardian2DiplomaId, int athleteGuardian1JobId, int athleteGuardian2JobId,
			String identifiedId,String identifiedName,int identifiedGender, Date identifiedBirthday,
			String identifiedAddress,String identifiedNationality, int athleteFootsize,
			int athleteHeight,int athleteWeight, int athletePosition, String studentId, 
			String athleteSchoolyear, String athletePhone, String photoBase64, String coachName);

	/**
	 * 
	 * 根据运动员ID删除运动员信息
	 * 
	 * @param athleteId
	 *            运动员ID
	 * @return boolean 布尔值
	 *
	 */
	public boolean deleteAthlete(int athleteId);

	/**
	 * 
	 * 根据运动员ID查询运动员信息
	 * 
	 * @param athleteId
	 * @return YsmsAthlete 运动员POJO
	 *
	 */
	public YsmsAthlete getAthleteByID(int athleteId);

	/**
	 * 
	 * 根据运动员所属学校，身份证号码，姓名，性别，位置查询
	 * 
	 * @param schoolId
	 *            学校ID
	 * @param identifiedId
	 *            身份证号码
	 * @param identifiedName
	 *            姓名
	 * @param identifiedGender
	 *            性别
	 * @param athletePosition
	 *            位置
	 * @param studentId
	 * 			       学籍id
	 * @param athleteSchoolyear
	 * 			     入学年份
	 * @return List<YsmsAthlete> 运动员POJO的LIST集合
	 *
	 */
	public List<YsmsAthlete> getAtheletes(Integer schoolId, String identifiedId,
			String identifiedName, Integer identifiedGender,
			Integer athletePosition,Integer studentId,String athleteSchoolyear);
	/**
	 * 
	 * 根据运动员所属学校，身份证号码，姓名，性别，位置分页查询
	 * 
	 * @param schoolId
	 *            学校ID
	 * @param identifiedId
	 *            身份证号码
	 * @param identifiedName
	 *            姓名
	 * @param identifiedGender
	 *            性别
	 * @param athletePosition
	 *            位置
	 * @param studentId
	 * 			       学籍id
	 * @param athleteSchoolyear
	 * 			     入学年份
	 * @param page
	 *            分页
	 * @return List<YsmsAthlete> 运动员POJO的LIST集合
	 *
	 */
	public List<YsmsAthlete> getAtheletesByPage(Integer schoolId, String identifiedId,
			String identifiedName, Integer identifiedGender,
			Integer athletePosition,Integer studentId,String athleteSchoolyear,Page page);
	
	public int findByFuzzyQueryAndPageCount(Integer schoolId, String identifiedId,
			String identifiedName, Integer identifiedGender,
			Integer athletePosition,Integer studentId,String athleteSchoolyear);
	/**
	 * 获取全部职业
	 * @return
	 */
	public List<YsmsJobs> getAllJobs();
	
	/**
	 * 获取全部学历
	 * @return
	 */
	public List<YsmsDiploma> getAllDiplomas();

	public List<YsmsAthlete> getAtheletesByPageAndOrder(Integer schoolId,
			String identifiedId, String identifiedName,
			Integer identifiedGender, Integer athletePosition,
			Integer studentId, String athleteSchoolyear, Page page,
			String orderString);

	public YsmsAthleteAtt getAthleteAttByAthleteID(int athleteId);
	
	/**
	 * 根据运动员身份证，查找该身份证的运动员是否已经存在
	 * @param identifiedId
	 * @return
	 */
	public boolean isAddingAthletePermitted(String identifiedId);
	
}
