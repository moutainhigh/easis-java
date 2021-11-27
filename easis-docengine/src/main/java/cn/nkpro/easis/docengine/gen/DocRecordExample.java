package cn.nkpro.easis.docengine.gen;

import java.util.ArrayList;
import java.util.List;

public class DocRecordExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
    public DocRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDocIdIsNull() {
            addCriterion("DOC_ID is null");
            return (Criteria) this;
        }

        public Criteria andDocIdIsNotNull() {
            addCriterion("DOC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDocIdEqualTo(String value) {
            addCriterion("DOC_ID =", value, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdNotEqualTo(String value) {
            addCriterion("DOC_ID <>", value, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdGreaterThan(String value) {
            addCriterion("DOC_ID >", value, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOC_ID >=", value, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdLessThan(String value) {
            addCriterion("DOC_ID <", value, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdLessThanOrEqualTo(String value) {
            addCriterion("DOC_ID <=", value, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdLike(String value) {
            addCriterion("DOC_ID like", value, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdNotLike(String value) {
            addCriterion("DOC_ID not like", value, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdIn(List<String> values) {
            addCriterion("DOC_ID in", values, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdNotIn(List<String> values) {
            addCriterion("DOC_ID not in", values, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdBetween(String value1, String value2) {
            addCriterion("DOC_ID between", value1, value2, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdNotBetween(String value1, String value2) {
            addCriterion("DOC_ID not between", value1, value2, "docId");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("VERSION not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andStateOriginalIsNull() {
            addCriterion("STATE_ORIGINAL is null");
            return (Criteria) this;
        }

        public Criteria andStateOriginalIsNotNull() {
            addCriterion("STATE_ORIGINAL is not null");
            return (Criteria) this;
        }

        public Criteria andStateOriginalEqualTo(String value) {
            addCriterion("STATE_ORIGINAL =", value, "stateOriginal");
            return (Criteria) this;
        }

        public Criteria andStateOriginalNotEqualTo(String value) {
            addCriterion("STATE_ORIGINAL <>", value, "stateOriginal");
            return (Criteria) this;
        }

        public Criteria andStateOriginalGreaterThan(String value) {
            addCriterion("STATE_ORIGINAL >", value, "stateOriginal");
            return (Criteria) this;
        }

        public Criteria andStateOriginalGreaterThanOrEqualTo(String value) {
            addCriterion("STATE_ORIGINAL >=", value, "stateOriginal");
            return (Criteria) this;
        }

        public Criteria andStateOriginalLessThan(String value) {
            addCriterion("STATE_ORIGINAL <", value, "stateOriginal");
            return (Criteria) this;
        }

        public Criteria andStateOriginalLessThanOrEqualTo(String value) {
            addCriterion("STATE_ORIGINAL <=", value, "stateOriginal");
            return (Criteria) this;
        }

        public Criteria andStateOriginalLike(String value) {
            addCriterion("STATE_ORIGINAL like", value, "stateOriginal");
            return (Criteria) this;
        }

        public Criteria andStateOriginalNotLike(String value) {
            addCriterion("STATE_ORIGINAL not like", value, "stateOriginal");
            return (Criteria) this;
        }

        public Criteria andStateOriginalIn(List<String> values) {
            addCriterion("STATE_ORIGINAL in", values, "stateOriginal");
            return (Criteria) this;
        }

        public Criteria andStateOriginalNotIn(List<String> values) {
            addCriterion("STATE_ORIGINAL not in", values, "stateOriginal");
            return (Criteria) this;
        }

        public Criteria andStateOriginalBetween(String value1, String value2) {
            addCriterion("STATE_ORIGINAL between", value1, value2, "stateOriginal");
            return (Criteria) this;
        }

        public Criteria andStateOriginalNotBetween(String value1, String value2) {
            addCriterion("STATE_ORIGINAL not between", value1, value2, "stateOriginal");
            return (Criteria) this;
        }

        public Criteria andStateOriginalDescIsNull() {
            addCriterion("STATE_ORIGINAL_DESC is null");
            return (Criteria) this;
        }

        public Criteria andStateOriginalDescIsNotNull() {
            addCriterion("STATE_ORIGINAL_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andStateOriginalDescEqualTo(String value) {
            addCriterion("STATE_ORIGINAL_DESC =", value, "stateOriginalDesc");
            return (Criteria) this;
        }

        public Criteria andStateOriginalDescNotEqualTo(String value) {
            addCriterion("STATE_ORIGINAL_DESC <>", value, "stateOriginalDesc");
            return (Criteria) this;
        }

        public Criteria andStateOriginalDescGreaterThan(String value) {
            addCriterion("STATE_ORIGINAL_DESC >", value, "stateOriginalDesc");
            return (Criteria) this;
        }

        public Criteria andStateOriginalDescGreaterThanOrEqualTo(String value) {
            addCriterion("STATE_ORIGINAL_DESC >=", value, "stateOriginalDesc");
            return (Criteria) this;
        }

        public Criteria andStateOriginalDescLessThan(String value) {
            addCriterion("STATE_ORIGINAL_DESC <", value, "stateOriginalDesc");
            return (Criteria) this;
        }

        public Criteria andStateOriginalDescLessThanOrEqualTo(String value) {
            addCriterion("STATE_ORIGINAL_DESC <=", value, "stateOriginalDesc");
            return (Criteria) this;
        }

        public Criteria andStateOriginalDescLike(String value) {
            addCriterion("STATE_ORIGINAL_DESC like", value, "stateOriginalDesc");
            return (Criteria) this;
        }

        public Criteria andStateOriginalDescNotLike(String value) {
            addCriterion("STATE_ORIGINAL_DESC not like", value, "stateOriginalDesc");
            return (Criteria) this;
        }

        public Criteria andStateOriginalDescIn(List<String> values) {
            addCriterion("STATE_ORIGINAL_DESC in", values, "stateOriginalDesc");
            return (Criteria) this;
        }

        public Criteria andStateOriginalDescNotIn(List<String> values) {
            addCriterion("STATE_ORIGINAL_DESC not in", values, "stateOriginalDesc");
            return (Criteria) this;
        }

        public Criteria andStateOriginalDescBetween(String value1, String value2) {
            addCriterion("STATE_ORIGINAL_DESC between", value1, value2, "stateOriginalDesc");
            return (Criteria) this;
        }

        public Criteria andStateOriginalDescNotBetween(String value1, String value2) {
            addCriterion("STATE_ORIGINAL_DESC not between", value1, value2, "stateOriginalDesc");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("STATE is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("STATE is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("STATE =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("STATE <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("STATE >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("STATE >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("STATE <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("STATE <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("STATE like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("STATE not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("STATE in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("STATE not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("STATE between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("STATE not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateDescIsNull() {
            addCriterion("STATE_DESC is null");
            return (Criteria) this;
        }

        public Criteria andStateDescIsNotNull() {
            addCriterion("STATE_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andStateDescEqualTo(String value) {
            addCriterion("STATE_DESC =", value, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescNotEqualTo(String value) {
            addCriterion("STATE_DESC <>", value, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescGreaterThan(String value) {
            addCriterion("STATE_DESC >", value, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescGreaterThanOrEqualTo(String value) {
            addCriterion("STATE_DESC >=", value, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescLessThan(String value) {
            addCriterion("STATE_DESC <", value, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescLessThanOrEqualTo(String value) {
            addCriterion("STATE_DESC <=", value, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescLike(String value) {
            addCriterion("STATE_DESC like", value, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescNotLike(String value) {
            addCriterion("STATE_DESC not like", value, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescIn(List<String> values) {
            addCriterion("STATE_DESC in", values, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescNotIn(List<String> values) {
            addCriterion("STATE_DESC not in", values, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescBetween(String value1, String value2) {
            addCriterion("STATE_DESC between", value1, value2, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescNotBetween(String value1, String value2) {
            addCriterion("STATE_DESC not between", value1, value2, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andCardNamesIsNull() {
            addCriterion("CARD_NAMES is null");
            return (Criteria) this;
        }

        public Criteria andCardNamesIsNotNull() {
            addCriterion("CARD_NAMES is not null");
            return (Criteria) this;
        }

        public Criteria andCardNamesEqualTo(String value) {
            addCriterion("CARD_NAMES =", value, "cardNames");
            return (Criteria) this;
        }

        public Criteria andCardNamesNotEqualTo(String value) {
            addCriterion("CARD_NAMES <>", value, "cardNames");
            return (Criteria) this;
        }

        public Criteria andCardNamesGreaterThan(String value) {
            addCriterion("CARD_NAMES >", value, "cardNames");
            return (Criteria) this;
        }

        public Criteria andCardNamesGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_NAMES >=", value, "cardNames");
            return (Criteria) this;
        }

        public Criteria andCardNamesLessThan(String value) {
            addCriterion("CARD_NAMES <", value, "cardNames");
            return (Criteria) this;
        }

        public Criteria andCardNamesLessThanOrEqualTo(String value) {
            addCriterion("CARD_NAMES <=", value, "cardNames");
            return (Criteria) this;
        }

        public Criteria andCardNamesLike(String value) {
            addCriterion("CARD_NAMES like", value, "cardNames");
            return (Criteria) this;
        }

        public Criteria andCardNamesNotLike(String value) {
            addCriterion("CARD_NAMES not like", value, "cardNames");
            return (Criteria) this;
        }

        public Criteria andCardNamesIn(List<String> values) {
            addCriterion("CARD_NAMES in", values, "cardNames");
            return (Criteria) this;
        }

        public Criteria andCardNamesNotIn(List<String> values) {
            addCriterion("CARD_NAMES not in", values, "cardNames");
            return (Criteria) this;
        }

        public Criteria andCardNamesBetween(String value1, String value2) {
            addCriterion("CARD_NAMES between", value1, value2, "cardNames");
            return (Criteria) this;
        }

        public Criteria andCardNamesNotBetween(String value1, String value2) {
            addCriterion("CARD_NAMES not between", value1, value2, "cardNames");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserRealnameIsNull() {
            addCriterion("USER_REALNAME is null");
            return (Criteria) this;
        }

        public Criteria andUserRealnameIsNotNull() {
            addCriterion("USER_REALNAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserRealnameEqualTo(String value) {
            addCriterion("USER_REALNAME =", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameNotEqualTo(String value) {
            addCriterion("USER_REALNAME <>", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameGreaterThan(String value) {
            addCriterion("USER_REALNAME >", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_REALNAME >=", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameLessThan(String value) {
            addCriterion("USER_REALNAME <", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameLessThanOrEqualTo(String value) {
            addCriterion("USER_REALNAME <=", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameLike(String value) {
            addCriterion("USER_REALNAME like", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameNotLike(String value) {
            addCriterion("USER_REALNAME not like", value, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameIn(List<String> values) {
            addCriterion("USER_REALNAME in", values, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameNotIn(List<String> values) {
            addCriterion("USER_REALNAME not in", values, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameBetween(String value1, String value2) {
            addCriterion("USER_REALNAME between", value1, value2, "userRealname");
            return (Criteria) this;
        }

        public Criteria andUserRealnameNotBetween(String value1, String value2) {
            addCriterion("USER_REALNAME not between", value1, value2, "userRealname");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("SOURCE =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("SOURCE <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("SOURCE >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCE >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("SOURCE <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("SOURCE <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("SOURCE like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("SOURCE not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("SOURCE in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("SOURCE not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("SOURCE between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("SOURCE not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andLogGroupIdIsNull() {
            addCriterion("LOG_GROUP_ID is null");
            return (Criteria) this;
        }

        public Criteria andLogGroupIdIsNotNull() {
            addCriterion("LOG_GROUP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLogGroupIdEqualTo(String value) {
            addCriterion("LOG_GROUP_ID =", value, "logGroupId");
            return (Criteria) this;
        }

        public Criteria andLogGroupIdNotEqualTo(String value) {
            addCriterion("LOG_GROUP_ID <>", value, "logGroupId");
            return (Criteria) this;
        }

        public Criteria andLogGroupIdGreaterThan(String value) {
            addCriterion("LOG_GROUP_ID >", value, "logGroupId");
            return (Criteria) this;
        }

        public Criteria andLogGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("LOG_GROUP_ID >=", value, "logGroupId");
            return (Criteria) this;
        }

        public Criteria andLogGroupIdLessThan(String value) {
            addCriterion("LOG_GROUP_ID <", value, "logGroupId");
            return (Criteria) this;
        }

        public Criteria andLogGroupIdLessThanOrEqualTo(String value) {
            addCriterion("LOG_GROUP_ID <=", value, "logGroupId");
            return (Criteria) this;
        }

        public Criteria andLogGroupIdLike(String value) {
            addCriterion("LOG_GROUP_ID like", value, "logGroupId");
            return (Criteria) this;
        }

        public Criteria andLogGroupIdNotLike(String value) {
            addCriterion("LOG_GROUP_ID not like", value, "logGroupId");
            return (Criteria) this;
        }

        public Criteria andLogGroupIdIn(List<String> values) {
            addCriterion("LOG_GROUP_ID in", values, "logGroupId");
            return (Criteria) this;
        }

        public Criteria andLogGroupIdNotIn(List<String> values) {
            addCriterion("LOG_GROUP_ID not in", values, "logGroupId");
            return (Criteria) this;
        }

        public Criteria andLogGroupIdBetween(String value1, String value2) {
            addCriterion("LOG_GROUP_ID between", value1, value2, "logGroupId");
            return (Criteria) this;
        }

        public Criteria andLogGroupIdNotBetween(String value1, String value2) {
            addCriterion("LOG_GROUP_ID not between", value1, value2, "logGroupId");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("UPDATED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("UPDATED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Long value) {
            addCriterion("UPDATED_TIME =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Long value) {
            addCriterion("UPDATED_TIME <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Long value) {
            addCriterion("UPDATED_TIME >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("UPDATED_TIME >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Long value) {
            addCriterion("UPDATED_TIME <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Long value) {
            addCriterion("UPDATED_TIME <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Long> values) {
            addCriterion("UPDATED_TIME in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Long> values) {
            addCriterion("UPDATED_TIME not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Long value1, Long value2) {
            addCriterion("UPDATED_TIME between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Long value1, Long value2) {
            addCriterion("UPDATED_TIME not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table nk_doc_record
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
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