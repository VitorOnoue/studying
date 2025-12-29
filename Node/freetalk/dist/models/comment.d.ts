import mongoose from 'mongoose';
declare const Comment: mongoose.Model<{
    content?: string | null;
    userName?: string | null;
}, {}, {}, {}, mongoose.Document<unknown, {}, {
    content?: string | null;
    userName?: string | null;
}, {}, mongoose.DefaultSchemaOptions> & {
    content?: string | null;
    userName?: string | null;
} & {
    _id: mongoose.Types.ObjectId;
} & {
    __v: number;
}, mongoose.Schema<any, mongoose.Model<any, any, any, any, any, any>, {}, {}, {}, {}, mongoose.DefaultSchemaOptions, {
    content?: string | null;
    userName?: string | null;
}, mongoose.Document<unknown, {}, mongoose.FlatRecord<{
    content?: string | null;
    userName?: string | null;
}>, {}, mongoose.ResolveSchemaOptions<mongoose.DefaultSchemaOptions>> & mongoose.FlatRecord<{
    content?: string | null;
    userName?: string | null;
}> & {
    _id: mongoose.Types.ObjectId;
} & {
    __v: number;
}>>;
export default Comment;
//# sourceMappingURL=comment.d.ts.map