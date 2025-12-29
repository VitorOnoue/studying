import mongoose from 'mongoose';
const commentSchema = new mongoose.Schema({
    userName: {
        type: String,
        require: true
    },
    content: {
        type: String,
        require: true
    },
});
const Comment = mongoose.model('Comment', commentSchema);
export default Comment;
//# sourceMappingURL=comment.js.map