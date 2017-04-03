SELECT @rank := @rank + 1 as ranking, v.name, v.votes
FROM (SELECT @rank := 0 ) rt,votes v
ORDER BY v.votes DESC;