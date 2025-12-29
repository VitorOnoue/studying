import * as dotenv from 'dotenv';
dotenv.config();

import express, { Request, Response, NextFunction } from 'express';
import mongoose from 'mongoose';
import cors from 'cors';
import { newPostRouter, deletePostRouter, updatePostRouter, showPostRouter, newCommentRouter, deleteCommentRouter } from './routers/index.js';

const PORT = 8080;

const app = express();

app.use(cors({
    origin: '*',
    optionsSuccessStatus: 200
}));

declare global {
    interface CustomError extends Error {
        status?: number
    }
}

app.use(express.urlencoded({
    extended: false
}));
app.use(express.json());

// post routers
app.use(newPostRouter);
app.use(deletePostRouter);
app.use(updatePostRouter);
app.use(showPostRouter);

// comment routers
app.use(newCommentRouter);
app.use(deleteCommentRouter);

app.use((req, res, next) => {
    const error = new Error('not found') as CustomError;
    console.log('caiu aqui');
    error.status = 404;
    next(error);
})

app.use((error: CustomError, req: Request, res: Response, next: NextFunction) => {
    if(error.status) {
        return res.status(error.status).json({ message: error.message });
    }
    res.status(500).json({ message: 'internal server error' });
});

const start = async () => {
    if (!process.env.MONGO_URL) {
        throw new Error('MONGO_URL is not defined');
    }
    try {
        await mongoose.connect(process.env.MONGO_URL);
    } catch (err) {
        throw new Error('Could not connect to database');
    }
    app.listen(PORT, () => console.log(`server is up! running on port ${PORT}`));
}

start();