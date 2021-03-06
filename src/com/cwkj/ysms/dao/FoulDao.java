package com.cwkj.ysms.dao;

import java.util.List;

import com.cwkj.ysms.model.YsmsFoul;
import com.cwkj.ysms.util.Page;

/**
 * 
 * 比赛犯规表数据处理接口
 * 
 * @author seed
 * @since 2015-3-10
 *
 */
public interface FoulDao {
	
	/**
	 * 保存犯规信息
	 * @param ysmsFoul
	 */
	public void save(YsmsFoul ysmsFoul);
	
	/**
	 * 删除犯规信息
	 * @param ysmsFourl
	 */
	public void delete(YsmsFoul ysmsFoul);
	
	
	/**
	 * 查询犯规信息
	 * @param foulId 犯规信息Id
	 */
	public YsmsFoul findById(int foulId);
	
	
	/**
	 * 查询所有犯规信息
	 * @return
	 */
	public List<YsmsFoul> findAll();
	
	
	/**
	 * 根据比赛场次获取犯规信息
	 * @param gamesId 比赛Id
	 * @return
	 */
	public List<YsmsFoul> getYsmsFoulsByGamesId(int gamesId);
	
	
	/**
	 * 获取球员的犯规信息
	 * @param athleteId 球员Id
	 * @return
	 */
	public List<YsmsFoul> getYsmsFoulsByAthleteId(int athleteId);

	public List<YsmsFoul> findByGame(int gamesId);
}
