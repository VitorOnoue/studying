import { Router } from 'express';
import { User } from '../../models/user.js';
import { authenticationService } from '../../common/index.js';
import jwt from 'jsonwebtoken';
const router = Router();
router.post('/signin', async (req, res, next) => {
    const { email, password } = req.body;
    const user = await User.findOne({ email });
    if (!user) {
        const error = new Error('wrong credentials');
        error.status = 400;
        return next(error);
    }
    const isEqual = await authenticationService.pwdCompare(user.password, password);
    if (!isEqual) {
        const error = new Error('wrong credentials');
        error.status = 400;
        return next(error);
    }
    const token = jwt.sign({ email: email, userId: user._id }, process.env.JWT_KEY, { expiresIn: '1h' });
    req.session = { jwt: token };
    res.status(200).send(user);
});
export { router as signinRouter };
//# sourceMappingURL=signin.js.map