import React from 'react';
import ReactSelect from 'react-select';

export default (props) => {
    return (
        <ReactSelect
            theme={(theme) => ({
                ...theme,
                borderRadius: 0,
                colors: {
                    ...theme.colors,
                    primary25: '#1F96F3',
                    primary: 'black',
                },
            })}
            {...props} //copying all others props passed from the container
        />
    )
}