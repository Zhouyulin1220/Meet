package com.lin.meet.main.fragment.Home;

public interface HomeConstract {
    interface TopicView{
        void initTopicAdapter();
        int insertTopic(TopicAdapter.TopicBean bean);
        int insertTopic(int position,TopicAdapter.TopicBean bean);
        void endRefresh();
        void likeResult(int resultCode,int position,boolean like);
        void setLike(int position,boolean like);
        void endLoadMore();
        void isNetError(boolean error);
    }
    interface TopicPresenter{
        void onInitTopics(int flag);//flag = 0默认 flag = 1 具体id flag = 2刷新
        void onInsertTopics();
        void onInsertTopic(String id);
        void onClickLike(boolean like,String id,String uid,int position);
        void onInsertToTop();
    }
    interface VideoView{
        void refreshVideos();
        void insertVideo(int position,VideoBean bean);
        void insertVideo(VideoBean bean);
        void endRefresh();
        void endLoadMore();
        void setNetError(boolean error);
    }
    interface VideoPresenter{
        void onInitVideos(int flag);//flag = 0默认 flag = 1 具体id flag = 2刷新
        void onInsertVideos();
        void onInsertVideo(String id);
        void onInsertToTop();
    }
}
