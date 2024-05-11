--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

-- Started on 2024-05-11 18:37:46

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 33646)
-- Name: comment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comment (
    id integer NOT NULL,
    news_id integer NOT NULL,
    text text NOT NULL,
    like_number integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.comment OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 33661)
-- Name: comment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.comment_id_seq OWNER TO postgres;

--
-- TOC entry 3338 (class 0 OID 0)
-- Dependencies: 217
-- Name: comment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.comment_id_seq OWNED BY public.comment.id;


--
-- TOC entry 214 (class 1259 OID 33638)
-- Name: news; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.news (
    id integer NOT NULL,
    text text NOT NULL,
    like_number integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.news OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 33659)
-- Name: news_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.news_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE public.news_id_seq OWNER TO postgres;

--
-- TOC entry 3339 (class 0 OID 0)
-- Dependencies: 216
-- Name: news_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.news_id_seq OWNED BY public.news.id;


--
-- TOC entry 3180 (class 2604 OID 33662)
-- Name: comment id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment ALTER COLUMN id SET DEFAULT nextval('public.comment_id_seq'::regclass);


--
-- TOC entry 3178 (class 2604 OID 33660)
-- Name: news id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news ALTER COLUMN id SET DEFAULT nextval('public.news_id_seq'::regclass);


--
-- TOC entry 3330 (class 0 OID 33646)
-- Dependencies: 215
-- Data for Name: comment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.comment (id, news_id, text, like_number) VALUES (1, 1, 'ого', 0);
INSERT INTO public.comment (id, news_id, text, like_number) VALUES (2, 1, 'uwu', 0);
INSERT INTO public.comment (id, news_id, text, like_number) VALUES (3, 1, 'bimbimbambam', 0);
INSERT INTO public.comment (id, news_id, text, like_number) VALUES (4, 1, 'bimbimbambam', 0);
INSERT INTO public.comment (id, news_id, text, like_number) VALUES (5, 3, 'SKYYYYYYYR', 0);
INSERT INTO public.comment (id, news_id, text, like_number) VALUES (6, 3, 'SKYYYYYYYR!!!!!!!', 0);
INSERT INTO public.comment (id, news_id, text, like_number) VALUES (7, 3, 'SKYYYYYYYR!!!!!!!AAAAAAAAAAAAa', 0);
INSERT INTO public.comment (id, news_id, text, like_number) VALUES (8, 3, 'shittttttttt', 0);
INSERT INTO public.comment (id, news_id, text, like_number) VALUES (59, 3, 'pizdec', 0);


--
-- TOC entry 3329 (class 0 OID 33638)
-- Dependencies: 214
-- Data for Name: news; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.news (id, text, like_number) VALUES (1, 'новость', 0);
INSERT INTO public.news (id, text, like_number) VALUES (2, 'новая новость', 0);
INSERT INTO public.news (id, text, like_number) VALUES (3, 'skyyyyyyyr', 0);


--
-- TOC entry 3340 (class 0 OID 0)
-- Dependencies: 217
-- Name: comment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comment_id_seq', 59, true);


--
-- TOC entry 3341 (class 0 OID 0)
-- Dependencies: 216
-- Name: news_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.news_id_seq', 3, true);


--
-- TOC entry 3183 (class 2606 OID 33645)
-- Name: news News_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT "News_pkey" PRIMARY KEY (id);


--
-- TOC entry 3185 (class 2606 OID 33653)
-- Name: comment comment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- TOC entry 3186 (class 2606 OID 33654)
-- Name: comment fkey_news_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkey_news_id FOREIGN KEY (news_id) REFERENCES public.news(id);


-- Completed on 2024-05-11 18:37:46

--
-- PostgreSQL database dump complete
--

