SELECT a.apnt_no,p.pt_name,	p.pt_no, d.mcdp_cd,	d.dr_name,a.apnt_ymd
FROM appointment a JOIN doctor d ON a.mddr_id = d.dr_id JOIN patient p ON a.pt_no = p.pt_no
WHERE DATE_FORMAT(a.apnt_ymd,'%Y-%m-%d') = '2022-04-13' AND a.apnt_cncl_yn = 'N' AND d.mcdp_cd = 'CS'
ORDER BY a.apnt_ymd;
