import { Router, Request, Response, NextFunction, request } from 'express';
import { User } from '../../models/user.js';
import { authenticationService, BadRequestError } from '../../common/index.js';
import jwt from 'jsonwebtoken';

const router = Router();

router.post('/signin', async (req: Request, res: Response, next: NextFunction) => {
    const { email, password } = req.body;

    const user = await User.findOne({ email });
    if (!user) return next(new BadRequestError('wrong credentials!'));

    const isEqual = await authenticationService.pwdCompare(user.password, password);
    if (!isEqual) return next(new BadRequestError('wrong credentials!'));
    
    const token = jwt.sign({ email: email, userId: user._id }, process.env.JWT_KEY!, { expiresIn: '1h' });

    req.session = { jwt: token };

    res.status(200).send(user);

});

export { router as signinRouter }