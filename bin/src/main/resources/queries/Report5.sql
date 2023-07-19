with responded_institutions(institution_id)as(
select distinct um.institution_id
from user_master um,mshe_data md
where um.email_id=md.email_id and year=:year
),
non_responded_institutions(aishe_code,state_id,institution_name,email_id)as(
select distinct on(i.aishe_code)i.aishe_code,i.state_id,i.name,um.email_id
from institution_details i,user_master um
where i.id=um.institution_id and i.id not in(select institution_id from responded_institutions)

)select state_id, institution_name,aishe_code,email_id from non_responded_institutions
order by state_id