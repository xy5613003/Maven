package com.xk.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ATaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ATaskExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andBugIsNull() {
            addCriterion("bug is null");
            return (Criteria) this;
        }

        public Criteria andBugIsNotNull() {
            addCriterion("bug is not null");
            return (Criteria) this;
        }

        public Criteria andBugEqualTo(String value) {
            addCriterion("bug =", value, "bug");
            return (Criteria) this;
        }

        public Criteria andBugNotEqualTo(String value) {
            addCriterion("bug <>", value, "bug");
            return (Criteria) this;
        }

        public Criteria andBugGreaterThan(String value) {
            addCriterion("bug >", value, "bug");
            return (Criteria) this;
        }

        public Criteria andBugGreaterThanOrEqualTo(String value) {
            addCriterion("bug >=", value, "bug");
            return (Criteria) this;
        }

        public Criteria andBugLessThan(String value) {
            addCriterion("bug <", value, "bug");
            return (Criteria) this;
        }

        public Criteria andBugLessThanOrEqualTo(String value) {
            addCriterion("bug <=", value, "bug");
            return (Criteria) this;
        }

        public Criteria andBugLike(String value) {
            addCriterion("bug like", value, "bug");
            return (Criteria) this;
        }

        public Criteria andBugNotLike(String value) {
            addCriterion("bug not like", value, "bug");
            return (Criteria) this;
        }

        public Criteria andBugIn(List<String> values) {
            addCriterion("bug in", values, "bug");
            return (Criteria) this;
        }

        public Criteria andBugNotIn(List<String> values) {
            addCriterion("bug not in", values, "bug");
            return (Criteria) this;
        }

        public Criteria andBugBetween(String value1, String value2) {
            addCriterion("bug between", value1, value2, "bug");
            return (Criteria) this;
        }

        public Criteria andBugNotBetween(String value1, String value2) {
            addCriterion("bug not between", value1, value2, "bug");
            return (Criteria) this;
        }

        public Criteria andJhstarttimeIsNull() {
            addCriterion("jhstarttime is null");
            return (Criteria) this;
        }

        public Criteria andJhstarttimeIsNotNull() {
            addCriterion("jhstarttime is not null");
            return (Criteria) this;
        }

        public Criteria andJhstarttimeEqualTo(Date value) {
            addCriterion("jhstarttime =", value, "jhstarttime");
            return (Criteria) this;
        }

        public Criteria andJhstarttimeNotEqualTo(Date value) {
            addCriterion("jhstarttime <>", value, "jhstarttime");
            return (Criteria) this;
        }

        public Criteria andJhstarttimeGreaterThan(Date value) {
            addCriterion("jhstarttime >", value, "jhstarttime");
            return (Criteria) this;
        }

        public Criteria andJhstarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("jhstarttime >=", value, "jhstarttime");
            return (Criteria) this;
        }

        public Criteria andJhstarttimeLessThan(Date value) {
            addCriterion("jhstarttime <", value, "jhstarttime");
            return (Criteria) this;
        }

        public Criteria andJhstarttimeLessThanOrEqualTo(Date value) {
            addCriterion("jhstarttime <=", value, "jhstarttime");
            return (Criteria) this;
        }

        public Criteria andJhstarttimeIn(List<Date> values) {
            addCriterion("jhstarttime in", values, "jhstarttime");
            return (Criteria) this;
        }

        public Criteria andJhstarttimeNotIn(List<Date> values) {
            addCriterion("jhstarttime not in", values, "jhstarttime");
            return (Criteria) this;
        }

        public Criteria andJhstarttimeBetween(Date value1, Date value2) {
            addCriterion("jhstarttime between", value1, value2, "jhstarttime");
            return (Criteria) this;
        }

        public Criteria andJhstarttimeNotBetween(Date value1, Date value2) {
            addCriterion("jhstarttime not between", value1, value2, "jhstarttime");
            return (Criteria) this;
        }

        public Criteria andJhendtimeIsNull() {
            addCriterion("jhendtime is null");
            return (Criteria) this;
        }

        public Criteria andJhendtimeIsNotNull() {
            addCriterion("jhendtime is not null");
            return (Criteria) this;
        }

        public Criteria andJhendtimeEqualTo(Date value) {
            addCriterion("jhendtime =", value, "jhendtime");
            return (Criteria) this;
        }

        public Criteria andJhendtimeNotEqualTo(Date value) {
            addCriterion("jhendtime <>", value, "jhendtime");
            return (Criteria) this;
        }

        public Criteria andJhendtimeGreaterThan(Date value) {
            addCriterion("jhendtime >", value, "jhendtime");
            return (Criteria) this;
        }

        public Criteria andJhendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("jhendtime >=", value, "jhendtime");
            return (Criteria) this;
        }

        public Criteria andJhendtimeLessThan(Date value) {
            addCriterion("jhendtime <", value, "jhendtime");
            return (Criteria) this;
        }

        public Criteria andJhendtimeLessThanOrEqualTo(Date value) {
            addCriterion("jhendtime <=", value, "jhendtime");
            return (Criteria) this;
        }

        public Criteria andJhendtimeIn(List<Date> values) {
            addCriterion("jhendtime in", values, "jhendtime");
            return (Criteria) this;
        }

        public Criteria andJhendtimeNotIn(List<Date> values) {
            addCriterion("jhendtime not in", values, "jhendtime");
            return (Criteria) this;
        }

        public Criteria andJhendtimeBetween(Date value1, Date value2) {
            addCriterion("jhendtime between", value1, value2, "jhendtime");
            return (Criteria) this;
        }

        public Criteria andJhendtimeNotBetween(Date value1, Date value2) {
            addCriterion("jhendtime not between", value1, value2, "jhendtime");
            return (Criteria) this;
        }

        public Criteria andSjstarttimeIsNull() {
            addCriterion("sjstarttime is null");
            return (Criteria) this;
        }

        public Criteria andSjstarttimeIsNotNull() {
            addCriterion("sjstarttime is not null");
            return (Criteria) this;
        }

        public Criteria andSjstarttimeEqualTo(Date value) {
            addCriterion("sjstarttime =", value, "sjstarttime");
            return (Criteria) this;
        }

        public Criteria andSjstarttimeNotEqualTo(Date value) {
            addCriterion("sjstarttime <>", value, "sjstarttime");
            return (Criteria) this;
        }

        public Criteria andSjstarttimeGreaterThan(Date value) {
            addCriterion("sjstarttime >", value, "sjstarttime");
            return (Criteria) this;
        }

        public Criteria andSjstarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sjstarttime >=", value, "sjstarttime");
            return (Criteria) this;
        }

        public Criteria andSjstarttimeLessThan(Date value) {
            addCriterion("sjstarttime <", value, "sjstarttime");
            return (Criteria) this;
        }

        public Criteria andSjstarttimeLessThanOrEqualTo(Date value) {
            addCriterion("sjstarttime <=", value, "sjstarttime");
            return (Criteria) this;
        }

        public Criteria andSjstarttimeIn(List<Date> values) {
            addCriterion("sjstarttime in", values, "sjstarttime");
            return (Criteria) this;
        }

        public Criteria andSjstarttimeNotIn(List<Date> values) {
            addCriterion("sjstarttime not in", values, "sjstarttime");
            return (Criteria) this;
        }

        public Criteria andSjstarttimeBetween(Date value1, Date value2) {
            addCriterion("sjstarttime between", value1, value2, "sjstarttime");
            return (Criteria) this;
        }

        public Criteria andSjstarttimeNotBetween(Date value1, Date value2) {
            addCriterion("sjstarttime not between", value1, value2, "sjstarttime");
            return (Criteria) this;
        }

        public Criteria andSjendtimeIsNull() {
            addCriterion("sjendtime is null");
            return (Criteria) this;
        }

        public Criteria andSjendtimeIsNotNull() {
            addCriterion("sjendtime is not null");
            return (Criteria) this;
        }

        public Criteria andSjendtimeEqualTo(Date value) {
            addCriterion("sjendtime =", value, "sjendtime");
            return (Criteria) this;
        }

        public Criteria andSjendtimeNotEqualTo(Date value) {
            addCriterion("sjendtime <>", value, "sjendtime");
            return (Criteria) this;
        }

        public Criteria andSjendtimeGreaterThan(Date value) {
            addCriterion("sjendtime >", value, "sjendtime");
            return (Criteria) this;
        }

        public Criteria andSjendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sjendtime >=", value, "sjendtime");
            return (Criteria) this;
        }

        public Criteria andSjendtimeLessThan(Date value) {
            addCriterion("sjendtime <", value, "sjendtime");
            return (Criteria) this;
        }

        public Criteria andSjendtimeLessThanOrEqualTo(Date value) {
            addCriterion("sjendtime <=", value, "sjendtime");
            return (Criteria) this;
        }

        public Criteria andSjendtimeIn(List<Date> values) {
            addCriterion("sjendtime in", values, "sjendtime");
            return (Criteria) this;
        }

        public Criteria andSjendtimeNotIn(List<Date> values) {
            addCriterion("sjendtime not in", values, "sjendtime");
            return (Criteria) this;
        }

        public Criteria andSjendtimeBetween(Date value1, Date value2) {
            addCriterion("sjendtime between", value1, value2, "sjendtime");
            return (Criteria) this;
        }

        public Criteria andSjendtimeNotBetween(Date value1, Date value2) {
            addCriterion("sjendtime not between", value1, value2, "sjendtime");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userid like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userid not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andTesteridIsNull() {
            addCriterion("testerid is null");
            return (Criteria) this;
        }

        public Criteria andTesteridIsNotNull() {
            addCriterion("testerid is not null");
            return (Criteria) this;
        }

        public Criteria andTesteridEqualTo(String value) {
            addCriterion("testerid =", value, "testerid");
            return (Criteria) this;
        }

        public Criteria andTesteridNotEqualTo(String value) {
            addCriterion("testerid <>", value, "testerid");
            return (Criteria) this;
        }

        public Criteria andTesteridGreaterThan(String value) {
            addCriterion("testerid >", value, "testerid");
            return (Criteria) this;
        }

        public Criteria andTesteridGreaterThanOrEqualTo(String value) {
            addCriterion("testerid >=", value, "testerid");
            return (Criteria) this;
        }

        public Criteria andTesteridLessThan(String value) {
            addCriterion("testerid <", value, "testerid");
            return (Criteria) this;
        }

        public Criteria andTesteridLessThanOrEqualTo(String value) {
            addCriterion("testerid <=", value, "testerid");
            return (Criteria) this;
        }

        public Criteria andTesteridLike(String value) {
            addCriterion("testerid like", value, "testerid");
            return (Criteria) this;
        }

        public Criteria andTesteridNotLike(String value) {
            addCriterion("testerid not like", value, "testerid");
            return (Criteria) this;
        }

        public Criteria andTesteridIn(List<String> values) {
            addCriterion("testerid in", values, "testerid");
            return (Criteria) this;
        }

        public Criteria andTesteridNotIn(List<String> values) {
            addCriterion("testerid not in", values, "testerid");
            return (Criteria) this;
        }

        public Criteria andTesteridBetween(String value1, String value2) {
            addCriterion("testerid between", value1, value2, "testerid");
            return (Criteria) this;
        }

        public Criteria andTesteridNotBetween(String value1, String value2) {
            addCriterion("testerid not between", value1, value2, "testerid");
            return (Criteria) this;
        }

        public Criteria andProjectidIsNull() {
            addCriterion("projectid is null");
            return (Criteria) this;
        }

        public Criteria andProjectidIsNotNull() {
            addCriterion("projectid is not null");
            return (Criteria) this;
        }

        public Criteria andProjectidEqualTo(String value) {
            addCriterion("projectid =", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotEqualTo(String value) {
            addCriterion("projectid <>", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidGreaterThan(String value) {
            addCriterion("projectid >", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidGreaterThanOrEqualTo(String value) {
            addCriterion("projectid >=", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidLessThan(String value) {
            addCriterion("projectid <", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidLessThanOrEqualTo(String value) {
            addCriterion("projectid <=", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidLike(String value) {
            addCriterion("projectid like", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotLike(String value) {
            addCriterion("projectid not like", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidIn(List<String> values) {
            addCriterion("projectid in", values, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotIn(List<String> values) {
            addCriterion("projectid not in", values, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidBetween(String value1, String value2) {
            addCriterion("projectid between", value1, value2, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotBetween(String value1, String value2) {
            addCriterion("projectid not between", value1, value2, "projectid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}