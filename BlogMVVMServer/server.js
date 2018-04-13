import express from 'express'
import dotenv from 'dotenv'
import morgan from 'morgan'
import bodyParser from 'body-parser'
import db from './db'

dotenv.config()

const ERROR_CODE = 0
const DATA_CODE = 1

const app = express()

app.use([
    morgan('dev'),
    bodyParser.urlencoded({ extended: true }),
    bodyParser.json()
])

app.get('/posts', (req, res) => {
    db.find({}, (error, posts) => {
        if (error) return res.status(200).send({
            error,
            code: ERROR_CODE
        })

        return res.status(200).send({
            data: posts,
            code: DATA_CODE
        })
    })
})

app.post('/post', (req, res) => {
    let { title, body } = req.body
    
    db.insert({
        title, 
        body
    }, (error, post) => {
        if (error) return res.status(200).send({
            error,
            code: ERROR_CODE
        })

        return res.status(200).send({
            data: post,
            code: DATA_CODE
        })
    })
})

app.put('/post', (req, res) => {
    let { id, title, body } = req.body
    
    db.update({ _id: id}, { title, body }, { returnUpdatedDocs: true }, (error, index, post) => {
        if (error) return res.status(200).send(error)

        return res.status(200).send({
            data: post,
            code: DATA_CODE
        })
    })
})

app.delete('/post', (req, res) => {
    let _id = req.body.id

    db.findOne({ _id }, (error, post) => {
        if (error) return res.status(200).send({
            error,
            code: ERROR_CODE
        })

        db.remove({ _id }, {}, (e, i) => {
            if (e) return res.status(200).send({
                error:e,
                code: ERROR_CODE
            })

            return res.status(200).send({
                data: post,
                code: DATA_CODE
            })
        })
    })
})

app.listen(process.env.PORT, () => console.log(`Server is running on port ${process.env.PORT}`))