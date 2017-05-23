package algorithm.leetcode.algorithm;
/*
 * medium
 * 355. Design Twitter 
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user 
 * and is able to see the 10 most recent tweets in the user's news feed. 
 * Your design should support the following methods:

    postTweet(userId, tweetId): Compose a new tweet.
    getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. 
    Each item in the news feed must be posted by users who the user followed or by the user herself. 
    Tweets must be ordered from most recent to least recent.
    follow(followerId, followeeId): Follower follows a followee.
    unfollow(followerId, followeeId): Follower unfollows a followee.

Example:

Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);

 */
import java.util.*;
class Tweet{
    int userId;
    int tweetId;
    int time;
    Tweet(int userId,int tweetId,int time){
        this.userId = userId;
        this.tweetId = tweetId;
        this.time = time;
    }
}
public class NO355_DesignTwitter {
	public static void main(String[] args) {
//		Twitter twitter = new Twitter();
//		twitter.postTweet(1, 5);
//		twitter.postTweet(1, 3);
//		twitter.postTweet(1, 101);
//		twitter.postTweet(1, 13);
//		twitter.postTweet(1, 10);
//		twitter.postTweet(1, 2);
//		twitter.postTweet(1, 94);
//		twitter.postTweet(1, 505);
//		twitter.postTweet(1, 333);
//		List<Integer> list = twitter.getNewsFeed(1);
//		for(Integer t : list){
//			System.out.print(t + " ");
//		}
	}
	//方法1：
	//利用LinkedList把推特保存起来，把最新的保存在前面，用Map保存用户的关注信息
	class Twitter {
//	    class Tweet{
//	        int userId;
//	        int tweetId;
//	        int time;
//	        Tweet(int userId,int tweetId,int time){
//	            this.userId = userId;
//	            this.tweetId = tweetId;
//	            this.time = time;
//	        }
//	    }
		private int time;
		private Map<Integer,Set<Integer>> followMap;
		private LinkedList<Tweet> queue;
		/** Initialize your data structure here. */
		public Twitter() {
			followMap = new HashMap<>();
//			queue = new PriorityQueue<Tweet>(1,new Comparator<Tweet>(){
//				public int compare(Tweet t1,Tweet t2){
//					return t2.time - t1.time;
//				}
//			});
	        queue = new LinkedList<>();
			time = 0;
		}
		
		/** Compose a new tweet. */
		public void postTweet(int userId, int tweetId) {
			queue.addFirst(new Tweet(userId,tweetId,time++));
		}
		
		/** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
		public List<Integer> getNewsFeed(int userId) {
			List<Integer> list = new ArrayList<>();
			Iterator<Tweet> it = queue.iterator();
			int count = 0;
			Tweet t;
			Set<Integer> set = followMap.get(userId);
			while(it.hasNext()){
				t = it.next();
				if(t.userId == userId || set != null && set.contains(t.userId)){
					list.add(t.tweetId);
					count++;
					if(count == 10){
						break;
					}
				}
			}
			return list;
		}
		
		/** Follower follows a followee. If the operation is invalid, it should be a no-op. */
		public void follow(int followerId, int followeeId) {
			if(followerId == followeeId){
				return;
			}
			if(!followMap.containsKey(followerId)){
				followMap.put(followerId,new HashSet<Integer>());
			}
			followMap.get(followerId).add(followeeId);
		}
		
		/** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
		public void unfollow(int followerId, int followeeId) {
			if(followerId == followeeId){
				return;
			}
			if(!followMap.containsKey(followerId)){
				followMap.put(followerId,new HashSet<Integer>());
			}
			followMap.get(followerId).remove(followeeId);
		}
	}
}
