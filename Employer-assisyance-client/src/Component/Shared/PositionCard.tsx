import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import Position from '../../Component/Shared/Assets/position.jpg';

export default function PositionCard() {
    return (
        <Card sx={{ maxWidth: 345 }}>
            <CardMedia
                component="img"
                height="140"
                image={Position}
                alt="biuro"
            />
            <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                    Awanse
                </Typography>
                <Typography variant="body2" color="text.secondary">
                   Możliwość awansowania/degradowania pracowników
                </Typography>
            </CardContent>
        </Card>
    );
}