package top.auok.cbps.ts.permission.dao;

import java.util.List;

import top.auok.cbps.ts.permission.entity.PmsMenuRole;

/**
 * 菜单角色关联表
 */
public interface PmsMenuRoleDao extends PermissionBaseDao<PmsMenuRole> {

	/**
	 * 根据角色ID删除菜单与角色的关联记录.
	 * 
	 * @param roleId
	 */
	void deleteByRoleId(Long roleId);

	/**
	 * 根据角色ID统计关联到此角色的菜单数.
	 * 
	 * @param roleId
	 *            角色ID.
	 * @return count.
	 */
	List<PmsMenuRole> listByRoleId(Long roleId);
}