package org.example.xyl.swordfingeroffer.set;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  学生数量
 * @author xiangyanlin
 * @date 2022/6/29
 */
public class MergeUserSolution {




    /**
     * @author xiangyanlin
     * @date 2022/6/29
     */
    public static class User implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * aId
         */
        private String aId;

        /**
         * bId
         */
        private String bId;


        /**
         * cId
         */
        private String cId;

        public User() {
        }

        public User(String aId, String bId, String cId) {
            this.aId = aId;
            this.bId = bId;
            this.cId = cId;
        }

        public String getaId() {
            return aId;
        }

        public void setaId(String aId) {
            this.aId = aId;
        }

        public String getbId() {
            return bId;
        }

        public void setbId(String bId) {
            this.bId = bId;
        }

        public String getcId() {
            return cId;
        }

        public void setcId(String cId) {
            this.cId = cId;
        }
    }


    public static void main(String[] args) {
        MergeUserSolution solution = new MergeUserSolution();
        List<User> users = new ArrayList<>();
        users.add(new User("1", "10", "13"));
        users.add(new User("2", "10", "37"));
        users.add(new User("400", "500", "37"));
        int i = solution.mergeUser(users);
        System.out.println(i);
    }

    /**
     *   (1 , 10 ,13) (2 , 10 ,37) (400 , 500 ,37)
     */
    public int mergeUser(List<User> users) {
        UnionFind.UnionSet<User> userUnionSet = new UnionFind.UnionSet<>(users);

        Map<String, User> mapA = new HashMap<>(16);
        Map<String, User> mapB = new HashMap<>(16);
        Map<String, User> mapC = new HashMap<>(16);
        for (User user : users) {
            if (mapA.containsKey(user.getaId())) {
                userUnionSet.union(user, mapA.get(user.getaId()));
            } else {
                mapA.put(user.getaId(), user);
            }
            if (mapB.containsKey(user.getbId())) {
                userUnionSet.union(user, mapB.get(user.getbId()));
            } else {
                mapB.put(user.getbId(), user);
            }
            if (mapC.containsKey(user.getcId())) {
                userUnionSet.union(user, mapC.get(user.getcId()));
            } else {
                mapC.put(user.getcId(), user);
            }
        }

        //向并查集询问 还有多个集合
        return userUnionSet.size();
    }




}
