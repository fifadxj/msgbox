    /* criteria query - listMessage */ select
        this_.ID as ID0_2_,
        this_.CATEGORY_ID as CATEGORY9_0_2_,
        this_.CONTENT as CONTENT0_2_,
        this_.CREATED_DATE as CREATED3_0_2_,
        this_.DISABLED as DISABLED0_2_,
        this_.MODIFIED_DATE as MODIFIED5_0_2_,
        this_.RANK as RANK0_2_,
        this_.SOURCE as SOURCE0_2_,
        this_.TOP as TOP0_2_,
        c1_.ID as ID1_0_,
        c1_.DESCRIPTION as DESCRIPT2_1_0_,
        c1_.NAME as NAME1_0_,
        c1_.PARENT_CATEGORY_ID as PARENT4_1_0_,
        category4_.ID as ID1_1_,
        category4_.DESCRIPTION as DESCRIPT2_1_1_,
        category4_.NAME as NAME1_1_,
        category4_.PARENT_CATEGORY_ID as PARENT4_1_1_ 
    from
        MESSAGES this_ 
    inner join
        CATEGORIES c1_ 
            on this_.CATEGORY_ID=c1_.ID 
    left outer join
        CATEGORIES category4_ 
            on c1_.PARENT_CATEGORY_ID=category4_.ID 
    where
        this_.DISABLED=? 
        and this_.CATEGORY_ID=c1_.ID 
        and (
            c1_.PARENT_CATEGORY_ID=? 
            or this_.CATEGORY_ID=?
        ) 
    order by
        this_.RANK desc