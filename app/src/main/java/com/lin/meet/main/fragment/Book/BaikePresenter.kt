package com.lin.meet.main.fragment.Book

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.lin.meet.bean.Baike
import java.util.*


class BaikePresenter(view: BaikeConstract.View): BaikeConstract.Presenter {
    override fun onInsertBaikeToTop() {
        view.initError()
        val query = BmobQuery<Baike>()
        query.setLimit(loadCount)
        query.setSkip(initCount)
        query.order("id")
        query.findObjects(object : FindListener<Baike>(){
            override fun done(p0: MutableList<Baike>?, p1: BmobException?) {
                if(p1==null&&p0!!.size>0){
                    view.initError()
                    initCount = (initCount+p0.size)%maxCount
                    for(index in 0 until p0.size){
                        view.insertBaikeToTop(index,p0[index])
                    }
                }else if(p1!=null){
                    view.error()
                }
                view.endLoading()
                view.endRefresh()
            }
        })
    }

    override fun onSearch(msg: String) {
        view.initAdapter()
        searchMsg(msg)
    }

    override fun onRefreshBaike() {
        val random = Random()
        val randomCount =1 + random.nextInt(initMaxCount)
        initCount = randomCount % initMaxCount
        view.initAdapter()
        onInsertBaike()
    }

    override fun onInsertBaike() {
        if(loading)return
        loading = true
        view.initError()
        val query = BmobQuery<Baike>()
        query.setLimit(loadCount)
        query.setSkip(initCount)
        query.order("id")
        query.findObjects(object : FindListener<Baike>(){
            override fun done(p0: MutableList<Baike>?, p1: BmobException?) {
                if(p1==null&&p0!!.size>0){
                    view.initError()
                    initCount = (initCount+p0.size)%maxCount
                    for(index in 0 until p0.size){
                        view.insertBaike(p0[index])
                    }
                }else if(p1!=null){
                    view.error()
                }
                view.endRefresh()
                view.endLoading()
                loading = false
            }
        })
    }

    private fun searchMsg(msg:String){
        val query = BmobQuery<Baike>()
        query.addWhereEqualTo("cnName",msg)
        query.findObjects(object : FindListener<Baike>(){
            override fun done(p0: MutableList<Baike>?, p1: BmobException?) {
                if(p1==null&&p0!!.size>0){
                    for(index in 0 until p0.size){
                        view.insertBaike(p0[index])
                    }
                    p0.clear()
                    view.endRefresh()
                }
            }
        })
    }

    private var loading = false
    private var initCount = 0
    private var loadCount = 10
    private val view = view
    private val maxCount = 2385
    private val initMaxCount = 1050
}