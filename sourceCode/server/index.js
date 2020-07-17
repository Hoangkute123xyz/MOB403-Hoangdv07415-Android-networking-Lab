let express = require('express');
const body = require('body-parser');
let app = express();

app.use(body.urlencoded({extended: false}));
app.use(body.json());
app.listen(8120);

app.get("/user", (req, res) => {
    res.status(200).send({name: req.query.name, score: req.query.score})
});
app.post("/square-calculate", (req, res) => {
    const width = parseFloat(req.body.width);
    const length = parseFloat(req.body.length);
    const c = (width + length) * 2;
    const s = width * length;
    res.status(200).send({chu_vi: c, dien_tich: s});
});

app.post("/cube-volume-calculate", (req, res) => {
    const value = parseFloat(req.body.value);
    const result = Math.pow(value, 3);
    res.status(200).send({volume: result});
});
app.post('/ptb2', (req, res) => {
    const a = parseFloat(req.body.a);
    const b = parseFloat(req.body.b);
    const c = parseFloat(req.body.c);

    const delta = b * b - 4 * a * c;
    if (a === 0) {
        if (c === 0) {
            if (b === 0) {
                res.status(200).send("Phuong trinh vo so nghiem")
            } else {
                res.status(200).send('x=0')
            }
        } else if (b === 0) {
            res.status(200).send("Phuong trinh vo nghiem")
        }else {
            res.status(200).send(`x = ${-c/b}`)
        }
    } else {
        if (delta===0){
            res.status(200).send(`x = ${-b/2*a}`)
        } else if (delta>0){
            const x1 = (-b-Math.sqrt(delta))/2*a;
            const x2 = (-b-Math.sqrt(delta))/2*a;
            res.status(200).send(`x1 = ${x1}, x2  = ${x2}`)
        } else {
            res.status(200).send(`Phuong trinh vo nghiem`)
        }
    }
});