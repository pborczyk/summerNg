export interface SpringErrorDto {
    timestamp: Date;
    status: number;
    error: string;
    message: string;
    trace: string;
    path: string;
}
