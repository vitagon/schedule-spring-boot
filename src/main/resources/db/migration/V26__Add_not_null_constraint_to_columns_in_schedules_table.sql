alter table if exists schedules
  alter group_id set not null,
  alter subgroup_id set not null,
  alter day_num set not null,
  alter week_type set not null,
  alter lesson_num set not null,
  alter lesson_type set not null,
  alter subject_id set not null;