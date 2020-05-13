import React from 'react';

import CircularProgress from '@material-ui/core/CircularProgress';

export default () => {
    return (
        <div style={{
            marginTop:"50%",
            marginRight:"50%",
            marginLeft:"50%",
        }}>
            <CircularProgress color='secondary'/>
        </div>
    )
}