-- SEQUENCE: public.abstract_abstract_id_seq

-- DROP SEQUENCE public.abstract_abstract_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.abstract_abstract_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
GO
ALTER SEQUENCE public.abstract_abstract_id_seq
    OWNER TO "test-profile";
GO
-- SEQUENCE: public.bidding_process_bid_id_seq

-- DROP SEQUENCE public.bidding_process_bid_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.bidding_process_bid_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
GO
ALTER SEQUENCE public.bidding_process_bid_id_seq
    OWNER TO "test-profile";
GO
-- SEQUENCE: public.conference_conference_id_seq

-- DROP SEQUENCE public.conference_conference_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.conference_conference_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
GO
ALTER SEQUENCE public.conference_conference_id_seq
    OWNER TO "test-profile";
GO
-- SEQUENCE: public.meta_info_meta_info_id_seq

-- DROP SEQUENCE public.meta_info_meta_info_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.meta_info_meta_info_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
GO
ALTER SEQUENCE public.meta_info_meta_info_id_seq
    OWNER TO "test-profile";
GO
-- SEQUENCE: public.paper_paper_id_seq

-- DROP SEQUENCE public.paper_paper_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.paper_paper_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE public.paper_paper_id_seq
    OWNER TO "test-profile";
GO
-- SEQUENCE: public.participant_list_participant_list_id_seq

-- DROP SEQUENCE public.participant_list_participant_list_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.participant_list_participant_list_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
GO
ALTER SEQUENCE public.participant_list_participant_list_id_seq
    OWNER TO "test-profile";
GO
-- SEQUENCE: public.permission_permission_id_seq

-- DROP SEQUENCE public.permission_permission_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.permission_permission_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
GO
ALTER SEQUENCE public.permission_permission_id_seq
    OWNER TO "test-profile";
GO
-- SEQUENCE: public.proposal_proposal_id_seq

-- DROP SEQUENCE public.proposal_proposal_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.proposal_proposal_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
GO
ALTER SEQUENCE public.proposal_proposal_id_seq
    OWNER TO "test-profile";
GO
-- SEQUENCE: public.qualifier_qualifier_id_seq

-- DROP SEQUENCE public.qualifier_qualifier_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.qualifier_qualifier_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
GO
ALTER SEQUENCE public.qualifier_qualifier_id_seq
    OWNER TO "test-profile";
GO
-- SEQUENCE: public.recommendation_recommendation_id_seq

-- DROP SEQUENCE public.recommendation_recommendation_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.recommendation_recommendation_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
GO
ALTER SEQUENCE public.recommendation_recommendation_id_seq
    OWNER TO "test-profile";
GO
-- SEQUENCE: public.review_list_review_id_seq

-- DROP SEQUENCE public.review_list_review_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.review_list_review_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
GO
ALTER SEQUENCE public.review_list_review_id_seq
    OWNER TO "test-profile";
GO
-- SEQUENCE: public.role_role_id_seq

-- DROP SEQUENCE public.role_role_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.role_role_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
GO
ALTER SEQUENCE public.role_role_id_seq
    OWNER TO "test-profile";
GO
-- SEQUENCE: public.section_section_id_seq

-- DROP SEQUENCE public.section_section_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.section_section_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
GO
ALTER SEQUENCE public.section_section_id_seq
    OWNER TO "test-profile";
GO
-- SEQUENCE: public.ticketing_ticket_id_seq

-- DROP SEQUENCE public.ticketing_ticket_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.ticketing_ticket_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
GO
ALTER SEQUENCE public.ticketing_ticket_id_seq
    OWNER TO "test-profile";
GO
-- SEQUENCE: public.user_info_user_info_id_seq

-- DROP SEQUENCE public.user_info_user_info_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.user_info_user_info_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
GO
ALTER SEQUENCE public.user_info_user_info_id_seq
    OWNER TO "test-profile";
GO
-- SEQUENCE: public.user_user_id_seq

-- DROP SEQUENCE public.user_user_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.user_user_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;
GO
ALTER SEQUENCE public.user_user_id_seq
    OWNER TO "test-profile";
GO
-- Table: public.user_info

-- DROP TABLE public.user_info;

CREATE TABLE IF NOT EXISTS public.user_info
(
    user_info_id    integer NOT NULL DEFAULT nextval('user_info_user_info_id_seq'::regclass),
    name            character varying(40) COLLATE pg_catalog."default",
    affiliation     character varying(60) COLLATE pg_catalog."default",
    email_address   character varying(50) COLLATE pg_catalog."default",
    webpage_address character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT user_info_pkey PRIMARY KEY (user_info_id)
)
GO

ALTER TABLE public.user_info
    OWNER to "test-profile";
GO
-- Table: public."user"

-- DROP TABLE public."user";

CREATE TABLE IF NOT EXISTS public."user"
(
    user_id      integer                                            NOT NULL DEFAULT nextval('user_user_id_seq'::regclass),
    username     character varying(20) COLLATE pg_catalog."default" NOT NULL,
    password     character varying(20) COLLATE pg_catalog."default" NOT NULL,
    is_validated boolean                                            NOT NULL,
    user_info_id integer,
    CONSTRAINT user_pkey PRIMARY KEY (user_id),
    CONSTRAINT "FK_User_Info_ID_USER_INFO" FOREIGN KEY (user_info_id)
        REFERENCES public.user_info (user_info_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
GO

ALTER TABLE public."user"
    OWNER to "test-profile";
GO

-- Table: public.conference

-- DROP TABLE public.conference;

CREATE TABLE IF NOT EXISTS public.conference
(
    conference_id     integer NOT NULL DEFAULT nextval('conference_conference_id_seq'::regclass),
    name              character varying(40) COLLATE pg_catalog."default",
    start_date        date,
    end_date          date,
    proposal_deadline date,
    paper_deadline    date,
    CONSTRAINT conference_pkey PRIMARY KEY (conference_id)
)
GO

ALTER TABLE public.conference
    OWNER to "test-profile";
GO
-- Table: public.bidding_process

-- DROP TABLE public.bidding_process;

CREATE TABLE IF NOT EXISTS public.bidding_process
(
    bid_id        integer NOT NULL DEFAULT nextval('bidding_process_bid_id_seq'::regclass),
    conference_id integer,
    deadline      date,
    CONSTRAINT bidding_process_pkey PRIMARY KEY (bid_id),
    CONSTRAINT "FK_Conference_ID_CONFERENCE" FOREIGN KEY (conference_id)
        REFERENCES public.conference (conference_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
GO

ALTER TABLE public.bidding_process
    OWNER to "test-profile";
GO
-- Table: public.abstract

-- DROP TABLE public.abstract;

CREATE TABLE IF NOT EXISTS public.abstract
(
    abstract_id        integer NOT NULL DEFAULT nextval('abstract_abstract_id_seq'::regclass),
    format             character varying(5) COLLATE pg_catalog."default",
    byte_file_location character varying(40) COLLATE pg_catalog."default",
    CONSTRAINT abstract_pkey PRIMARY KEY (abstract_id)
)
GO

ALTER TABLE public.abstract
    OWNER to "test-profile";
GO
-- Table: public.meta_info

-- DROP TABLE public.meta_info;

CREATE TABLE IF NOT EXISTS public.meta_info
(
    meta_info_id integer NOT NULL DEFAULT nextval('meta_info_meta_info_id_seq'::regclass),
    name         character varying(40) COLLATE pg_catalog."default",
    keywords     character varying(40) COLLATE pg_catalog."default",
    topics       character varying(40) COLLATE pg_catalog."default",
    CONSTRAINT meta_info_pkey PRIMARY KEY (meta_info_id)
)
GO


ALTER TABLE public.meta_info
    OWNER to "test-profile";
GO

-- Table: public.paper

-- DROP TABLE public.paper;

CREATE TABLE IF NOT EXISTS public.paper
(
    paper_id           integer NOT NULL DEFAULT nextval('paper_paper_id_seq'::regclass),
    format             character varying(5) COLLATE pg_catalog."default",
    byte_file_location character varying(40) COLLATE pg_catalog."default",
    CONSTRAINT paper_pkey PRIMARY KEY (paper_id)
)
GO


ALTER TABLE public.paper
    OWNER to "test-profile";
GO



-- Table: public.proposal

-- DROP TABLE public.proposal;

CREATE TABLE IF NOT EXISTS public.proposal
(
    proposal_id  integer NOT NULL DEFAULT nextval('proposal_proposal_id_seq'::regclass),
    user_info_id integer,
    paper_id     integer,
    meta_info_id integer,
    abstract_id  integer,
    CONSTRAINT proposal_pkey PRIMARY KEY (proposal_id),
    CONSTRAINT "FK_Abstract_ID" FOREIGN KEY (abstract_id)
        REFERENCES public.abstract (abstract_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "FK_Meta_Info_ID" FOREIGN KEY (meta_info_id)
        REFERENCES public.meta_info (meta_info_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "FK_Paper_ID_PAPER" FOREIGN KEY (paper_id)
        REFERENCES public.paper (paper_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "FK_User_Info_ID_USER_INFO" FOREIGN KEY (user_info_id)
        REFERENCES public.user_info (user_info_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
GO

ALTER TABLE public.proposal
    OWNER to "test-profile";

GO
-- Table: public.analysis

-- DROP TABLE public.analysis;

CREATE TABLE IF NOT EXISTS public.analysis
(
    bid_id        integer,
    user_id       integer,
    proposal_id   integer,
    brief_analyse text COLLATE pg_catalog."default",
    refuse        boolean,
    CONSTRAINT "FK_Bid_ID_BIDDING_PROCESS" FOREIGN KEY (bid_id)
        REFERENCES public.bidding_process (bid_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_Proposal_ID_PROPOSAL" FOREIGN KEY (proposal_id)
        REFERENCES public.proposal (proposal_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_User_ID_USER" FOREIGN KEY (user_id)
        REFERENCES public."user" (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
GO

ALTER TABLE public.analysis
    OWNER to "test-profile";
GO
-- Table: public.author_list

-- DROP TABLE public.author_list;

CREATE TABLE IF NOT EXISTS public.author_list
(
    proposal_id integer NOT NULL,
    name        character varying(40) COLLATE pg_catalog."default",
    CONSTRAINT "FK_Proposal_ID_PROPOSAL" FOREIGN KEY (proposal_id)
        REFERENCES public.proposal (proposal_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
GO

ALTER TABLE public.author_list
    OWNER to "test-profile";
GO
-- Table: public.participant_list

-- DROP TABLE public.participant_list;

CREATE TABLE IF NOT EXISTS public.participant_list
(
    participant_list_id integer NOT NULL DEFAULT nextval('participant_list_participant_list_id_seq'::regclass),
    section_id          integer,
    user_id             integer,
    role_id             integer,
    CONSTRAINT participant_list_pkey PRIMARY KEY (participant_list_id)
)
GO

ALTER TABLE public.participant_list
    OWNER to "test-profile";
GO
-- Table: public.permission

-- DROP TABLE public.permission;

CREATE TABLE IF NOT EXISTS public.permission
(
    permission_id integer                                            NOT NULL DEFAULT nextval('permission_permission_id_seq'::regclass),
    name          character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT permission_pkey PRIMARY KEY (permission_id)
)
GO

ALTER TABLE public.permission
    OWNER to "test-profile";
GO
-- Table: public.permission_list

-- DROP TABLE public.permission_list;

CREATE TABLE IF NOT EXISTS public.permission_list
(
    user_id       integer NOT NULL,
    permission_id integer NOT NULL,
    CONSTRAINT "FK_Permission_ID_PERMISSION" FOREIGN KEY (permission_id)
        REFERENCES public.permission (permission_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_User_ID_USER" FOREIGN KEY (user_id)
        REFERENCES public."user" (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
GO

ALTER TABLE public.permission_list
    OWNER to "test-profile";
GO
-- Table: public.section

-- DROP TABLE public.section;

CREATE TABLE IF NOT EXISTS public.section
(
    section_id           integer NOT NULL DEFAULT nextval('section_section_id_seq'::regclass),
    supervisor_id        integer,
    conference_id        integer,
    date_of_presentation date,
    CONSTRAINT section_pkey PRIMARY KEY (section_id),
    CONSTRAINT "FK_Conference_ID_CONFERENCE" FOREIGN KEY (conference_id)
        REFERENCES public.conference (conference_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "FK_Supervisor_ID_USER" FOREIGN KEY (supervisor_id)
        REFERENCES public."user" (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
GO

ALTER TABLE public.section
    OWNER to "test-profile";
GO
-- Table: public.proposal_list

-- DROP TABLE public.proposal_list;

CREATE TABLE IF NOT EXISTS public.proposal_list
(
    section_id  integer NOT NULL,
    proposal_id integer NOT NULL,
    CONSTRAINT "FK_Proposal_ID_PROPOSAL" FOREIGN KEY (proposal_id)
        REFERENCES public.proposal (proposal_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "FK_Section_ID_SECTION" FOREIGN KEY (section_id)
        REFERENCES public.section (section_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
GO

ALTER TABLE public.proposal_list
    OWNER to "test-profile";
GO
-- Table: public.qualifier

-- DROP TABLE public.qualifier;

CREATE TABLE IF NOT EXISTS public.qualifier
(
    qualifier_id integer NOT NULL DEFAULT nextval('qualifier_qualifier_id_seq'::regclass),
    name         character varying(40) COLLATE pg_catalog."default",
    CONSTRAINT qualifier_pkey PRIMARY KEY (qualifier_id)
)
GO

ALTER TABLE public.qualifier
    OWNER to "test-profile";
GO
-- Table: public.recommendation

-- DROP TABLE public.recommendation;

CREATE TABLE IF NOT EXISTS public.recommendation
(
    recommendation_id      integer NOT NULL DEFAULT nextval('recommendation_recommendation_id_seq'::regclass),
    review_id              integer,
    recommendation_message text COLLATE pg_catalog."default",
    CONSTRAINT recommendation_pkey PRIMARY KEY (recommendation_id)
)
GO

ALTER TABLE public.recommendation
    OWNER to "test-profile";
GO
-- Table: public.review_list

-- DROP TABLE public.review_list;

CREATE TABLE IF NOT EXISTS public.review_list
(
    proposal_id  integer,
    qualifier_id integer,
    user_id      integer,
    review_id    integer NOT NULL DEFAULT nextval('review_list_review_id_seq'::regclass),
    CONSTRAINT review_list_pkey PRIMARY KEY (review_id),
    CONSTRAINT "FK_Proposal_ID_PROPOSAL" FOREIGN KEY (proposal_id)
        REFERENCES public.proposal (proposal_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_Qualifier_ID_QUALIFIER" FOREIGN KEY (qualifier_id)
        REFERENCES public.qualifier (qualifier_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_User_ID_USER" FOREIGN KEY (user_id)
        REFERENCES public."user" (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
GO

ALTER TABLE public.review_list
    OWNER to "test-profile";
GO
-- Table: public.role

-- DROP TABLE public.role;

CREATE TABLE IF NOT EXISTS public.role
(
    role_id integer NOT NULL DEFAULT nextval('role_role_id_seq'::regclass),
    name    character varying COLLATE pg_catalog."default",
    CONSTRAINT role_pkey PRIMARY KEY (role_id)
)
GO

ALTER TABLE public.role
    OWNER to "test-profile";
GO
-- Table: public.role_permission_id

-- DROP TABLE public.role_permission_id;

CREATE TABLE IF NOT EXISTS public.role_permission_id
(
    permission_id integer,
    role_id       integer
)
GO

ALTER TABLE public.role_permission_id
    OWNER to "test-profile";
GO
-- Table: public.role_user_list

-- DROP TABLE public.role_user_list;

CREATE TABLE IF NOT EXISTS public.role_user_list
(
    user_id integer,
    role_id integer
)
GO

ALTER TABLE public.role_user_list
    OWNER to "test-profile";
GO

-- Table: public.ticketing

-- DROP TABLE public.ticketing;

CREATE TABLE IF NOT EXISTS public.ticketing
(
    ticket_id  integer NOT NULL DEFAULT nextval('ticketing_ticket_id_seq'::regclass),
    user_id    integer,
    section_id integer,
    price      integer,
    CONSTRAINT ticketing_pkey PRIMARY KEY (ticket_id)
)
GO

ALTER TABLE public.ticketing
    OWNER to "test-profile";

GO

