create or replace
algorithm = UNDEFINED view `v_friend_ref` as
select
    `uufr`.`id` as `id`,
    `uufr`.`username` as `username`,
    `uufr`.`friend_username` as `friend_username`,
    `uufr`.`is_valid` as `is_valid`,
    `uux`.`face_img` as `friend_face_img`,
    `uux`.`nickname` as `friend_nickname`
from
    (`u_user_friend_ref` `uufr`
left join `u_user_xx` `uux` on
    ((`uufr`.`friend_username` = `uux`.`username`)))