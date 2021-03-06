package com.cwkj.ysms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cwkj.ysms.basedao.impl.GenericDaoImpl;
import com.cwkj.ysms.dao.LeagueDao;
import com.cwkj.ysms.model.YsmsLeague;
import com.cwkj.ysms.util.Page;

@Repository
public class LeagueDaoImpl extends GenericDaoImpl implements LeagueDao{
	private static final Log log = LogFactory.getLog(LeagueDaoImpl.class);
	@Override
	public void save(YsmsLeague ysmsLeague) {
		// TODO Auto-generated method stub
		log.debug("saving YsmsLeague instance");
		try {
			getSession().saveOrUpdate(ysmsLeague);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	@Override
	public void delete(YsmsLeague ysmsLeague) {
		// TODO Auto-generated method stub
		log.debug("deleting YsmsLeague instance");
		try {
			getSession().delete(ysmsLeague);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	@Override
	public YsmsLeague findById(int leagueId) {
		// TODO Auto-generated method stub
		log.debug("getting YsmsLeague instance with id: " + leagueId);
		try {
			YsmsLeague result = (YsmsLeague) getSession()
					.get("com.cwkj.ysms.model.YsmsLeague", leagueId);
			return result;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	@Override
	public List<YsmsLeague> findAll() {
		// TODO Auto-generated method stub
		log.debug("finding all YsmsLeague instances");
		try {
			String queryString = "from YsmsLeague where deleteflag = 0";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@Override
	public List<YsmsLeague> findByLeagueYear(int year) {
		// TODO Auto-generated method stub
		log.debug("finding YsmsLeague instance by year");
		try {
			String sql = " from YsmsLeague where year(league_year) = "+year + " and deleteflag=0 order by registerEndtime asc";
			List<Object> objects= findByHQL(sql);
			List<YsmsLeague> results = new ArrayList<YsmsLeague>();
			for(int i=0;i<objects.size();i++){
				YsmsLeague league = (YsmsLeague)objects.get(i);
				results.add(league);
			}
			log.debug("find by year successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by year failed", re);
			throw re;
		}
	}
	@Override
	public List<YsmsLeague> findByLeagueLevel(int level) {
		// TODO Auto-generated method stub
		log.debug("finding YsmsLeague instance by level");
		try {
			String sql = " from YsmsLeague as l where l.leagueLevel = "+level;
			List<Object> objects= findByHQL(sql);
			List<YsmsLeague> results = new ArrayList<YsmsLeague>();
			for(int i=0;i<objects.size();i++){
				YsmsLeague league = (YsmsLeague)objects.get(i);
				results.add(league);
			}
			log.debug("find by level successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by level failed", re);
			throw re;
		}
	}
	@Override
	public List<YsmsLeague> findAllByPage(Page page) {
		// TODO Auto-generated method stub
		
		log.debug("finding all YsmsLeague instances");
		try {
			String queryString = "from YsmsLeague";
			Query queryObject = getSession().createQuery(queryString);
			if(page != null){
				queryObject.setMaxResults(page.getEveryPage());
				queryObject.setFirstResult(page.getBeginIndex());
			}
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@Override
	public List<YsmsLeague> findByLeagueYearAndPage(int year, Page page) {
		// TODO Auto-generated method stub
		log.debug("finding YsmsLeague instance by year");
		try {
			String sql = " from YsmsLeague where year(league_year) = "+year;
			List<Object> objects= findByHQLAndPage(sql, page);
			List<YsmsLeague> results = new ArrayList<YsmsLeague>();
			for(int i=0;i<objects.size();i++){
				YsmsLeague league = (YsmsLeague)objects.get(i);
				results.add(league);
			}
			log.debug("find by year successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by year failed", re);
			throw re;
		}
	}
}
