package com.dao;

import java.util.List;

import com.domain.Goods;
import com.domain.GoodsType;

public class GoodsDao {

	public static void main(String[] args) {
		new GoodsDao().showType();
	}
	public List<GoodsType> showType(){
		CRUDDao<GoodsType> crud=new CRUDDao<GoodsType>(GoodsType.class);
		String sql="select * from goods_type";
		List<GoodsType> list=crud.display(sql);
		
		return list;
	}
	public List<Goods> showAll(){
		CRUDDao<Goods> crud=new CRUDDao<Goods>(Goods.class);
		String sql="select gid,gname,gsell,gcost,gcount,gtname from goods LEFT JOIN goods_type ON goods.gtid=goods_type.gtid";
		List<Goods> list=crud.display(sql);
		
		return list;
	}

}
