with stab(state_id,aishe_code,name,email_id,_1,_2,_3,_4,_5,_6,_7,_8,_9,_91,_10,_11,_12,_13,_14,_15,_16,_17,_18,_181,_19,_20,_21,_22,_23,_24,_25,_26,_27,_271) 
as
(
SELECT
  idd.state_id,idd.aishe_code,idd.name,um.email_id,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=1 and msd.year=:year2Before) AS _1,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=2 and msd.year=:year2Before) AS _2,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=3 and msd.year=:year2Before) AS _3,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=4 and msd.year=:year2Before) AS _4,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=5 and msd.year=:year2Before) AS _5,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=6 and msd.year=:year2Before) AS _6,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=7 and msd.year=:year2Before) AS _7,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=8 and msd.year=:year2Before) AS _8,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=9 and msd.year=:year2Before) AS _9,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=10 and msd.year=:year2Before) AS _91,


  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=1 and msd.year=:year1Before) AS _10,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=2 and msd.year=:year1Before) AS _11,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=3 and msd.year=:year1Before) AS _12,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=4 and msd.year=:year1Before) AS _13,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=5 and msd.year=:year1Before) AS _14,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=6 and msd.year=:year1Before) AS _15,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=7 and msd.year=:year1Before) AS _16,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=8 and msd.year=:year1Before) AS _17,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=9 and msd.year=:year1Before) AS _18,
 MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=10 and msd.year=:year1Before) AS _181,

  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=1 and msd.year=:year_one) AS _19,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=2 and msd.year=:year_one) AS _20,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=3 and msd.year=:year_one) AS _21,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=4 and msd.year=:year_one) AS _22,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=5 and msd.year=:year_one) AS _23,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=6 and msd.year=:year_one) AS _24,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=7 and msd.year=:year_one) AS _25,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=8 and msd.year=:year_one) AS _26,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=9 and msd.year=:year_one) AS _27,
 MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=10 and msd.year=:year_one) AS _271
 FROM 
mshe_data as msd,user_master as um,institution_details as idd
where 
um.email_id=msd.email_id and um.institution_id=idd.id  and year between :year2Before and :year_one 
GROUP BY idd.state_id,idd.aishe_code,idd.name,um.email_id  )

select state_id,name as institution_name,aishe_code,email_id,
case when stab._1 is not null then 'Yes' ELSE 'No' END as _1 ,
case when stab._2 is not null then 'Yes' ELSE 'No' END as _2,
case when stab._3  is not null then 'Yes' ELSE 'No' END as _3,
case when stab._4 is not null then 'Yes' ELSE 'No' END as _4,
case when stab._5 is not null then 'Yes' ELSE 'No' END as _5,
case when stab._6 is not null then 'Yes' ELSE 'No' END as _6,
case when stab._7 is not null then 'Yes' ELSE 'No' END as _7,
case when stab._8 is not null then 'Yes' ELSE 'No' END as _8,
case when stab._9 is not null then 'Yes' ELSE 'No' END as _9,
case when stab._91 is not null then 'Yes' ELSE 'No' END as _91,
case when stab._10 is not null then 'Yes' ELSE 'No' END as _10,
case when stab._11 is not null then 'Yes' ELSE 'No' END as _11,
case when stab._12 is not null then 'Yes' ELSE 'No' END as _12,
case when stab._13 is not null then 'Yes' ELSE 'No' END as _13,
case when stab._14 is not null then 'Yes' ELSE 'No' END as _14,
case when stab._15 is not null then 'Yes' ELSE 'No' END as _15,
case when stab._16 is not null then 'Yes' ELSE 'No' END as _16,
case when stab._17 is not null then 'Yes' ELSE 'No' END as _17,
case when stab._18 is not null then 'Yes' ELSE 'No' END as _18,
case when stab._181 is not null then 'Yes' ELSE 'No' END as _181,
case when stab._19 is not null then 'Yes' ELSE 'No' END as _19,
case when stab._20 is not null then 'Yes' ELSE 'No' END as _20,
case when stab._21 is not null then 'Yes' ELSE 'No' END as _21,
case when stab._22 is not null then 'Yes' ELSE 'No' END as _22,
case when stab._23 is not null then 'Yes' ELSE 'No' END as _23,
case when stab._24 is not null then 'Yes' ELSE 'No' END as _24,
case when stab._25 is not null then 'Yes' ELSE 'No' END as _25,
case when stab._26 is not null then 'Yes' ELSE 'No' END as _26,
case when stab._27 is not null then 'Yes' ELSE 'No' END as _27,
case when stab._271 is not null then 'Yes' ELSE 'No' END as _271
from stab

