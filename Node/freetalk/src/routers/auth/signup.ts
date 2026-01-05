import { Router, Request, Response, NextFunction } from 'express';
import { User } from '../../models/user.js';
import jwt from 'jsonwebtoken';

const router = Router();

router.post('/signup', async (req: Request, res: Response, next: NextFunction) => {
    const { email, password } = req.body;

    const user = await User.findOne({ email })

    if (user) {
        const error = new Error('user with this email already exists') as CustomError;
        error.status = 400;
        return next(error);
    }

    const newUser = new User({
        email,
        password
    });

    await newUser.save();

    req.session = {
        jwt: jwt.sign({ email: email, userId: newUser._id }, process.env.JWT_KEY!, { expiresIn: '1h' })
    }

    res.status(201).send(newUser);
});

export { router as signupRouter }