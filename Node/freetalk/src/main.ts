import * as dotenv from 'dotenv';
dotenv.config();

import express, { Request, Response, NextFunction } from 'express';
import mongoose from 'mongoose';

const PORT = 8080;

const app = express();


declare global {
    interface CustomError extends Error {
        status?: number
    }
}

app.use(express.urlencoded({
    extended: false
}));
app.use(express.json());

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