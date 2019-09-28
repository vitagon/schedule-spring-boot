alter table if exists schedules drop constraint uq_schedules_group_id_day_num_week_type_lesson_num;

alter table if exists schedules
  add constraint UQ_schedules_group_id_subgroup_id_day_num_week_type_lesson_num
  unique(group_id, subgroup_id, day_num, week_type, lesson_num);