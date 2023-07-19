 with stab(state_id,aishe_code,name,email_id,_1) as
 (SELECT
  idd.state_id,idd.aishe_code,idd.name,um.email_id,
  MAX(msd.date) FILTER (WHERE msd.monitoring_reort_data_id=:monitoring_report_id ) AS _1
 FROM  mshe_data as msd,user_master as um,institution_details as idd
 where um.email_id=msd.email_id and um.institution_id=idd.id  and year=:year and idd.state_id=:stateId or 'ALL'=:stateId
 GROUP BY idd.state_id,idd.aishe_code,idd.name,um.email_id  )

 select state_id as statecode,name as institutename,aishe_code as aishecode,
 case when stab._1 is not null then 'Yes' ELSE 'No' END as status
 from stab;