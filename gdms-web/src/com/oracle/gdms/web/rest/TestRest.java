package com.oracle.gdms.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.GoodsEntity;
import com.oracle.gdms.entity.GoodsModel;
import com.oracle.gdms.entity.GoodsType;
import com.oracle.gdms.entity.ResponseEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.util.Factory;

@Path("/a")
public class TestRest {
	@Path("/b")
	@GET
	public void b() {
		System.out.println("hello world!");
	}
    @Path("/push/one/{goodsid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public GoodsModel pushOne(@PathParam ("goodsid") int goodsid) {
		GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
		GoodsModel goods = service.findOne(goodsid);
		return goods;
	}
    
    @Path("/push/two")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public GoodsModel pushTwo(@QueryParam ("goodsid") int goodsid) {
		GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
		GoodsModel goods = service.findOne(goodsid);
		return goods;
    }
    
    @Path("/push/three")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	public GoodsModel pushThree(@FormParam ("goodsid") int goodsid) {
		GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
		GoodsModel goods = service.findOne(goodsid);
		return goods;
    }
    
    @Path("/push/four")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)//����Ĳ�����JSON��ʽ
	public GoodsModel pushFour(GoodsEntity g) {
		GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
		GoodsModel goods = service.findOne(g.getGoodsid());
		return goods;
    }
    
    @Path("/push/five")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)//����Ĳ�����JSON��ʽ
	public GoodsModel pushFive(String param) {
    	System.out.println("�������գ�");
    	System.out.println(param);
    	JSONObject j = JSONObject.parseObject(param);
    	System.out.println("id=" + j.getString("goodsid"));
    	System.out.println("name=" + j.getString("name"));
    	System.out.println("sex=" + j.getString("sex"));
		GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
//		GoodsModel goods = service.findOne(g.getGoodsid());
    	GoodsModel goods = service.findOne(Integer.parseInt(j.getString("goodsid")));
		return goods;

    }
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/push/goods/bytype")
    public List<GoodsModel> pushGoodsByType(GoodsType type){
    	List<GoodsModel> list = null;
    	GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
    	list = service.findByType(type.getGtid());
    	System.out.println("size=" + list.size());
    	return list;
    }
    
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/push/goods/one")
    public ResponseEntity pushGoods(String jsonstr){
    	JSONObject j = JSONObject.parseObject(jsonstr);
    	GoodsEntity goods = JSONObject.parseObject(j.getString("goods"), GoodsEntity.class);
    	System.out.println("goodsid=" + goods.getGoodsid());
    	System.out.println("name=" + goods.getName());
    	System.out.println("area=" + goods.getArea());
    	ResponseEntity rs = new ResponseEntity();
    	rs.setCode(0);
    	rs.setMessage("���ͳɹ�");
    	rs.setData(null);
    	return rs;
    }
}
