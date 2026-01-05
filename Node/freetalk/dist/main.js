import * as dotenv from 'dotenv';
dotenv.config();
import express from 'express';
import mongoose from 'mongoose';
import cors from 'cors';
import { newPostRouter, deletePostRouter, updatePostRouter, showPostRouter, newCommentRouter, deleteCommentRouter } from './routers/index.js';
import cookieSession from 'cookie-session';
import { requireAuth, currentUser } from './common/index.js';
const PORT = 8080;
const app = express();
app.use(cors({
    origin: '*',
    optionsSuccessStatus: 200
}));
app.set('trust proxy', true);
app.use(express.urlencoded({
    extended: false
}));
app.use(express.json());
app.use(cookieSession({
    signed: false,
    secure: false
}));
app.use(currentUser);
// post routers
app.use(requireAuth, newPostRouter);
app.use(requireAuth, deletePostRouter);
app.use(requireAuth, updatePostRouter);
app.use(showPostRouter);
// comment routers
app.use(requireAuth, newCommentRouter);
app.use(requireAuth, deleteCommentRouter);
app.use((req, res, next) => {
    const error = new Error('not found');
    console.log('caiu aqui');
    error.status = 404;
    next(error);
});
app.use((error, req, res, next) => {
    if (error.status) {
        return res.status(error.status).json({ message: error.message });
    }
    res.status(500).json({ message: 'internal server error' });
});
const start = async () => {
    if (!process.env.MONGO_URL) {
        throw new Error('MONGO_URL is not defined');
    }
    if (!process.env.JWT_KEY) {
        throw new Error('JWT_KEY is not defined');
    }
    try {
        await mongoose.connect(process.env.MONGO_URL);
    }
    catch (err) {
        throw new Error('Could not connect to database');
    }
    app.listen(PORT, () => console.log(`server is up! running on port ${PORT}`));
};
start();
//# sourceMappingURL=main.js.map