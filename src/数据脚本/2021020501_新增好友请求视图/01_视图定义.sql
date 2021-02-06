create or replace
algorithm = UNDEFINED view `v_request_xx` as
select
    `urx`.`id` as `id`,
    `urx`.`recipient_username` as `recipient_username`,
    `urx`.`sender_username` as `sender_username`,
    `urx`.`request_state` as `request_state`,
    `urx`.`is_valid` as `is_valid`,
    `uux`.`created_date` as `created_date`,
    `uux`.`face_img` as `face_img`,
    `uux`.`nickname` as `nickname`
from
    (`u_request_xx` `urx`
left join `u_user_xx` `uux` on
    ((`urx`.`sender_username` = `uux`.`username`)))