with total_institutions (total_institutions,state_id)as(
select count(institution_id)institution_id ,state_id
from institution_details i,user_master um
where um.institution_id=i.id and um.status=1 and role_id=2
group by state_id
),

responded_institutions(responded_institutions,year,state_id)as(
select count(distinct md.email_id) as responded_institutions,year,state_id
from mshe_data md,user_master um ,institution_details i
where md.email_id=um.email_id and um.institution_id=i.id and year=:year
group by md.year,i.state_id order by year
),

final_report(state_id,year,total,responded)as(
select t.state_id,r.year,t.total_institutions,case when r.responded_institutions !=0 then r.responded_institutions else 0 end
from total_institutions t left join responded_institutions r  on(t.state_id=r.state_id)
)

select state_id as stateCode,total as totalInstitutes,responded as respondedInstitutes from final_report