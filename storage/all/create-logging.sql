use KartRacing2
go

create table logInsert(
	id int primary key identity(1,1),
	logMessage varchar(100)
)

create table logInsertWithRecovery(
	id int primary key identity(1,1),
	logMessage varchar(100)
)

create table dirtyRead(
	id int primary key identity(1,1),
	tid int,
	logMessage varchar(100)	
)

create table logNonRepeatableRead(
	id int primary key identity(1,1),
	tid int,
	logMessage varchar(100)
)

create table logPhantomRead(
	id int primary key identity(1,1),
	tid int,
	logMessage varchar(100)
)

create table logDeadlock(
	id int primary key identity(1,1),
	tid int,
	logMessage varchar(100)
)

create table logUpdateConflict(
	id int primary key identity(1,1),
	tid int,
	logMessage varchar(100)
)