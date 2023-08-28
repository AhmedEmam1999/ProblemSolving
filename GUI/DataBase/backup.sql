--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

-- Started on 2023-08-28 21:37:26

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

--
-- TOC entry 3342 (class 0 OID 16477)
-- Dependencies: 218
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employees (id, department, gender, name, phone, salary, "Age") FROM stdin;
6	DG	Male	Lam	2458	65933.00	\N
3	FG	Female	nadia	65483	65893.00	\N
69	GF	Female	Lola	36523	52620.00	52.0
20	LK	Female	nelly	52320	86523.00	56.0
\.


--
-- TOC entry 3340 (class 0 OID 16468)
-- Dependencies: 216
-- Data for Name: Department ; Type: TABLE DATA; Schema: schema; Owner: postgres
--

COPY schema."Department " ("Department_ID", "Department_Name") FROM stdin;
1	SC
2	CS
3	MS
\.


--
-- TOC entry 3339 (class 0 OID 16460)
-- Dependencies: 215
-- Data for Name: Employee; Type: TABLE DATA; Schema: schema; Owner: postgres
--

COPY schema."Employee" ("ID", "Name", "Phone", "Gender", "Department ", "Salary") FROM stdin;
5	Ahmed	23541	M	SC	\N
10	Mahmoud	65865	M	MS	\N
3	Mona	51235	G	CS	\N
\.


--
-- TOC entry 3344 (class 0 OID 16484)
-- Dependencies: 220
-- Data for Name: employees; Type: TABLE DATA; Schema: schema; Owner: postgres
--

COPY schema.employees (id, department, gender, name, phone, salary, "Age") FROM stdin;
\.


--
-- TOC entry 3352 (class 0 OID 0)
-- Dependencies: 217
-- Name: employees_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employees_id_seq', 1, false);


--
-- TOC entry 3353 (class 0 OID 0)
-- Dependencies: 219
-- Name: employees_id_seq; Type: SEQUENCE SET; Schema: schema; Owner: postgres
--

SELECT pg_catalog.setval('schema.employees_id_seq', 1, false);


-- Completed on 2023-08-28 21:37:26

--
-- PostgreSQL database dump complete
--

