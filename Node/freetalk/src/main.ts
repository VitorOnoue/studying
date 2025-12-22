import * as dotenv from 'dotenv';
dotenv.config();

import express from 'express';
import mongoose from 'mongoose';

const PORT = 8080;

const app = express();

app.use(express.urlencoded({
    extended: false
}));
app.use(express.json());


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