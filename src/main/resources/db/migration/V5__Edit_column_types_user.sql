alter table public.pg_user alter column password type varchar using password::varchar;
alter table public.pg_user alter column login type varchar using login::varchar;